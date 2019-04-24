import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.repository.storage.WritePolicy
import groovy.json.JsonOutput

def create_docker_hosted(name, port) {
    repository.createDockerHosted(
        name,                                       // repo name
        port,                                       // http port
        null,                                       // https port
        BlobStoreManager.DEFAULT_BLOBSTORE_NAME,    // blob store
        true,                                       // v1Enabled
        true,                                       // strictContentTypeValidation
        WritePolicy.ALLOW                           // WritePolicy
    ) 
}

def create_docker_proxy(name, port, remote) {
    repository.createDockerProxy(
        name,                                       // repo name
        remote,                                     // remoteUrl
        'HUB',                                      // indexType
        null,                                       // indexUrl
        port,                                       // http port
        null,                                       // https port
        BlobStoreManager.DEFAULT_BLOBSTORE_NAME,    // blob store
        true,                                       // strictContentTypeValidation
        true                                        // v1Enabled
    ) 
}

def create_pypi_proxy(name, remote) {
    repository.createPyPiProxy(
        name,                                       // repo name
        remote,                                     // remoteUrl
        BlobStoreManager.DEFAULT_BLOBSTORE_NAME,    // blob store
        true                                        // strictContentTypeValidation
    ) 
}

def create_raw_hosted(name, remote) {
    repository.createRawProxy(
        name,                                       // repo name
        remote,                                     // remoteUrl
        BlobStoreManager.DEFAULT_BLOBSTORE_NAME,    // blob store
        true                                        // strictContentTypeValidation
    ) 
}

def create_raw_proxy(name, remote) {
    repository.createRawProxy(
        name,                                       // repo name
        remote,                                     // remoteUrl
        BlobStoreManager.DEFAULT_BLOBSTORE_NAME,    // blob store
        true                                        // strictContentTypeValidation
    ) 
}

def create_yum_proxy(name, remote) {
    repository.createYumProxy(
        name,                                       // repo name
        remote,                                     // remoteUrl
        BlobStoreManager.DEFAULT_BLOBSTORE_NAME,    // blob store
        true                                        // strictContentTypeValidation
    ) 
}

// Docker
// Hosted
create_docker_hosted('tungsten_ci', 5001)
create_docker_hosted('tungsten_nightly', 5002)
// Proxy
create_docker_proxy('proxy', 5005, 'https://registry-1.docker.io')
create_docker_proxy('access', 5010, 'https://registry-1.docker.io')

// PyPI
// Proxy
create_pypi_proxy('pypi', 'https://pypi.org')

// Raw
// Proxy
create_raw_proxy('ubuntu', 'http://mirror.yandex.ru/ubuntu')

// Yum
// Proxy
create_yum_proxy('docker-ce-stable',    'https://download.docker.com/linux/centos/7/x86_64/stable')
create_yum_proxy('openstack-newton',    'http://mirror.neu.edu.cn/centos/7/cloud/x86_64/openstack-newton')
create_yum_proxy('openstack-ocata',     'http://mirror.centos.org/centos/7/cloud/x86_64/openstack-ocata')
create_yum_proxy('openstack-queens',    'http://mirror.centos.org/centos/7/cloud/x86_64/openstack-queens')
create_yum_proxy('openstack-rocky',     'http://mirror.centos.org/centos/7/cloud/x86_64/openstack-rocky')
create_yum_proxy('centos74',            'http://mirror.yandex.ru/centos/7/os/x86_64')
create_yum_proxy('centos74-updates',    'https://mirror.yandex.ru/centos/7/updates/x86_64')
create_yum_proxy('centos74-extras',     'https://mirror.yandex.ru/centos/7/extras/x86_64')
create_yum_proxy('centos',              'https://mirror.yandex.ru')
create_yum_proxy('epel',                'https://mirror.yandex.ru/epel/7/x86_64')
// TODO: to be made hosted
create_yum_proxy('yum-tungsten-tpc',    'http://148.251.5.90/tpc')

// TODO: Hosted are not needed
//yum-tpc-test
//yum-tungsten-nightly
//yum-tungsten
