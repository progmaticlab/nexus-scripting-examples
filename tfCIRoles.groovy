
// Enable push of anonimous user to tungsten_ci docker registry
def tf_docker_priv = ['nx-repository-admin-docker-tungsten_ci-*', 'nx-repository-view-docker-tungsten_ci-*']
def tf_docker_role = ['tf_ci_docker']
security.addRole(
    'tf_ci_docker',
    'tf_ci_docker',
    'Role allowing pushing into TF CI docker registries',
    tf_docker_priv,
    null)
security.setUserRoles('anonymous', tf_docker_role)
