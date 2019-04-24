
// Enable push of anonimous user to tungsten_ci docker registry
def tf_docker_priv = ['nx-repository-admin-docker-tungsten_ci-*', 'nx-repository-view-docker-tungsten_ci-*']
security.addRole(
    'tf-ci-docker',
    'tf-ci-docker',
    'Role allowing pushing into TF CI docker registries',
    tf_docker_priv,
    []
)

def tf_anonimous_roles = ['nx-anonymous', 'tf-ci-docker']
security.setUserRoles('anonymous', tf_docker_role)
