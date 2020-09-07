/**
 * Alumno.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  tableName:'alumno',
  attributes: {
    nombre:{
      type:'string'
    },
    sexo:{
      type:'string'
    },
    fecha_nacimiento:{
      type:'string'
    },
    aulas:{
      //One to many colocamos en plural
      collection:'aula', //Referencia al modelo
       via:'alumnoAsignado' //Nombre de la foreign key
    }
   

  },

};

