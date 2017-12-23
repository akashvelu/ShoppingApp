const AWS = require('aws-sdk');
const docClient = new AWS.DynamoDB.DocumentClient({region: 'us-west-1'});

console.log('starting function');

function userExists(obj) {
    var bool = false; 
    for (var key in obj) {
        if(obj.hasOwnProperty(key)) {
            bool = true; 
        }
    }
    return bool; 
}

exports.handle = function(e, ctx, callback) {
  var params = {
    Item: {
      email: e.email,
      firstName: e.firstName,
      lastName: e.lastName,
      password: e.password,
      address: e.address,
      state: e.state
    },

    TableName: 'Users'
  };
  
  var checkParams = {
      TableName: 'Users',
      
      Key: {
          "email": e.email
      }
  }
  
  docClient.get(checkParams, function(err, data) {
      if(err) {
          callback(err, data); 
      }
      else {
          if(userExists(data)) {
              callback("Exists", null);
          }
          else {
              docClient.put(params, function(err2, data2) {
                  if(err2) {
                      callback(err2, null);
                  }
                  else {
                      callback(null, "Success");
                  }
              })
          }
      }
  }) 

}
