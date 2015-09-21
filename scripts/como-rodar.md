# Como rodar o ROS com o Simulador:

1. Rode o script *install-dependencies.sh*

2. Inicie o servidor:

        roslaunch rosbridge_server rosbridge_websocket.launch

3. em um outro terminal inicie o simulador:

        roslaunch hector_quadrotor_demo outdoor_flight_gazebo.launch
