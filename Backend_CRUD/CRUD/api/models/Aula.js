/**
 * Aula.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  tableName:'aulas',
  attributes: {
    materia:{
      type:'string'
    },

    numalumnos:{
      type:'number'
    },
    salonDisponible:{
      type:'boolean'
    },
    alumnoAsignado:{
      model:'alumno',
      required: true
    }


  },

};

