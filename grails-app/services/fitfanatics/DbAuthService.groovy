package fitfanatics

import grails.gorm.services.Service

@Service(DbAuth)
interface DbAuthService {

    DbAuth get(Serializable id)

    List<DbAuth> list(Map args)

    Long count()

    void delete(Serializable id)

    DbAuth save(DbAuth dbAuth)

}