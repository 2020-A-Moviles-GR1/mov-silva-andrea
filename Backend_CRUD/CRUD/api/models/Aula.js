/**
 * Aula.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

  materia:{
    type:'string'
  },
  numAlumnos:{
    type:'number'
    
  },
  salonDisponible:{
    type:'string',
    maxLength:2
  },
  alumnos:{
    type:'string'
  }

  },

};

