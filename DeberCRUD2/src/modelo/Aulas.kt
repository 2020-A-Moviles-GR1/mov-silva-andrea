package modelo

class Aulas(
        var id_aula: Int,
        var materia: String?,
        var numAlumnos: Int,
        var salonDisponible:Boolean,
        var idEstudiante:Int
){

override fun toString(): String {
    return "${id_aula},${materia},${numAlumnos},${salonDisponible},${idEstudiante}"
}
}
