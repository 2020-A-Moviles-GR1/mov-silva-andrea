/**
 * Alumno.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

   nombre:{
     type:'string',
     minLength:3
   },
   sexo:{
     type:'string',
     maxLength:1
   },
   fecha_nacimiento:{
     type:'string'
   },

   latitud:{
    type:'string'
   },
   longitud:{
    type:'string'
   },
   url:{
    type:'string'
   },

  

  },

};

