# org_any_app

OrganizApp es un trabajo final de universidad


### Ejemplo de Usuarios

 - hector@organizapp.cl
 - jaime@organizapp.cl
 - felipe@organizapp.cl
	
	
	
 - P4$$w0rd
  
	(Ingresar a este sitio) [http://perrosky.cl/restapi/v1/organizapp/ingresar]
	
### Perfiles

#### admin
#### ekono
#### vendedor



´´´
<?php
include_once '../include/Config.php';
require '../libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();


$app->post('/organizapp/ingresar', function() use ($app) {
    // check for required params
    verifyRequiredParams(array('usuario', 'password'));
     
    $response = array();
    
    //capturamos los parametros recibidos y los almacenamos como un nuevo array
    $param['usuario'] = $app->request->post('usuario');
    $param['password'] = $app->request->post('password');
    
     
    /* Podemos inicializar la conexion a la base de datos si queremos hacer uso de esta para procesar los parametros con DB */
    //$db = new DbHandler();
     
    /* Podemos crear un metodo que almacene el nuevo auto, por ejemplo: */
    //$auto = $db->createAuto($param);
     
    $retorno = 401;
    $response["error"] = true;
    
    if ( is_array($param) ) {
        
        if($param['usuario'] == "hector@organizapp.cl" ||
           $param['usuario'] == "jaime@organizapp.cl" ||
           $param['usuario'] == "felipe@organizapp.cl" ) {
               
            if($param['password'] == 'P4$$w0rd') {
                
                $datos = array();
                
                if($param['usuario'] == "hector@organizapp.cl"){
                    $datos["id"] = "1";
                    $datos["correo"] = "hector@organizapp.cl";
                    $datos["nombre"] = "Hector Raul";
                    $datos["apellido"] = "Ninez Jimenez";
                    $datos["perfil"] = "admin";
                }
                
                if($param['usuario'] == "jaime@organizapp.cl"){
                    $datos["id"] = "2";
                    $datos["correo"] = "jaime@organizapp.cl";
                    $datos["nombre"] = "Jaimini";
                    $datos["apellido"] = "Santini";
                    $datos["perfil"] = "ekono";
                }
                
                if($param['usuario'] == "felipe@organizapp.cl"){
                    $datos["id"] = "3";
                    $datos["correo"] = "felipe@organizapp.cl";
                    $datos["nombre"] = "Felipe";
                    $datos["apellido"] = "Pinto Sanchez";
                    $datos["perfil"] = "vendedor";
                }
                
                $retorno = 200;
                $response["error"] = false;
                $response["message"] = "Inicio de session correcto!";
                $response["data"] = $datos;
                
            } else {
                $response["message"] = "Password invalida. Por favor intenta nuevamente.";
            }
        } else {
            $response["message"] = "Usuario desconocido. Por favor intenta nuevamente.";
        }
    } else {
        $response["message"] = "Error al iniciar sesion. Por favor intenta nuevamente.";
    }
    
    echoResponse($retorno, $response);
});
$app->run();


function echoResponse($status_code, $response) {
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);
    // setting response content type to json
    $app->contentType('application/json');
    echo json_encode($response);
}

function verifyRequiredParams($required_fields) {
    $error = false;
    $error_fields = "";
    $request_params = array();
    $request_params = $_REQUEST;
    // Handling PUT request params
    if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
        $app = \Slim\Slim::getInstance();
        parse_str($app->request()->getBody(), $request_params);
    }
    foreach ($required_fields as $field) {
        if (!isset($request_params[$field]) || strlen(trim($request_params[$field])) <= 0) {
            $error = true;
            $error_fields .= $field . ', ';
        }
    }
    
    if ($error) {
        // Required field(s) are missing or empty
        // echo error json and stop the app
        $response = array();
        $app = \Slim\Slim::getInstance();
        $response["error"] = true;
        $response["message"] = 'Required field(s) ' . substr($error_fields, 0, -2) . ' is missing or empty';
        echoResponse(400, $response);
        
        $app->stop();
    }
}
´´´