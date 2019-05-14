
import org.sonatype.nexus.security.Roles
import org.sonatype.nexus.security.role.NoSuchRoleException
import org.sonatype.nexus.security.anonymous.AnonymousConfiguration
import org.sonatype.nexus.security.user.UserManager

// List of privileges for pushing into tungsten_ci docker registry
def tf_docker_priv = ['nx-repository-admin-docker-tungsten_ci-*', 'nx-repository-view-docker-tungsten_ci-*']

// Create or update tf docker role
def tf_docker_role_id = 'tf-ci-docker'
def tf_docker_role_name = tf_docker_role_id
def tf_docker_role_desc = 'Role allowing pushing into TF CI docker registries'
def existingRole = null
authManager = security.getSecuritySystem().getAuthorizationManager(UserManager.DEFAULT_SOURCE)
try {
    existingRole = authManager.getRole(tf_docker_role_name)
} catch (NoSuchRoleException ignored) {
    // could not find role
}
if (existingRole != null) {
    existingRole.setName(tf_docker_role_name)
    existingRole.setDescription(tf_docker_role_desc)
    existingRole.setPrivileges(tf_docker_priv)
    existingRole.setRoles([])
    authManager.updateRole(existingRole)
} else {
    security.addRole(
        tf_docker_role_id,
        tf_docker_role_name,
        tf_docker_role_desc,
        tf_docker_priv,
        []
    )
}

// Update anonimous user roles
def tf_anonimous_roles = [Roles.ANONYMOUS_ROLE_ID, tf_docker_role_id]
security.setUserRoles(AnonymousConfiguration.DEFAULT_USER_ID, tf_anonimous_roles)

// Enable anonimous access to the system
security.setAnonymousAccess(true)