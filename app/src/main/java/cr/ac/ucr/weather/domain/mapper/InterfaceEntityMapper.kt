package cr.ac.ucr.weather.domain.mapper

interface InterfaceEntityMapper<Entity, Model> {
    fun mapFromEntity(entity: Entity): Model
    fun entityFromModel(model: Model): Entity
}
