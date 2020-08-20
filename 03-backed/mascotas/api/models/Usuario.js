/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  tableName:'epn_usuario',
  attributes: {
    
   cedula:{ //nombre del atributo
    type:'string',
    required: true,
    columnName:'usr_cedula',
    unique:true,
    minLength:10,
    maxLength:25

   },
   nombre:{
     type:'string',
     minLength:3,
     required: true
   },
   correo:{

     type:'string',
     isEmail: true
   },
   estadoCivil:{
    type:'string',
    isIn:['Soltero','Casado','Divorciado','Viudio','Union libre'],
    defaultsTo:'Soltero'
   },
   password:{
     type:'string',
     regex:/^[a-zA-Z]\w{3,14}$/
   },
   pokemons:{
     //One to many colocamos en plural
     collection:'pokemon', //Referencia al modelo
      via:'usuario' //Nombre de la foreign key
   }

  },

};

