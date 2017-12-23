const AWS = require('aws-sdk');
const docClient = new AWS.DynamoDB.DocumentClient({region: 'us-west-1'});

function userExists(obj) {
    var bool = false; 
    for (var key in obj) {
        if(obj.hasOwnProperty(key)) {
            bool = true; 
        }
    }
    return bool; 
}

exports.handler = (e, context, callback) => {
    // TODO implement
    var submittedPassword = e.password; 
    
    var checkParams = {
        TableName: 'Users',
      
        Key: {
            "email": e.email
        }
    }
    
    docClient.get(checkParams, function(err, data) {
        if(err){
            callback(err, null);
        }
        else {
            if(userExists(data)) {
                password = data.Item.password;
                
                if (submittedPassword === password) {
                    callback(null, data);
                }
                else {
                    callback("Wrong password", null);
                }
                
            }
            else {
                callback("DNE", null);
            }
        }
    })
};
