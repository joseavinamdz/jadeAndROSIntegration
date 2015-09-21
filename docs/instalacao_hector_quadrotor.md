# Setup Hector Quadrotor
> [Documentação original](http://wiki.ros.org/hector_quadrotor)

> [Tutorial básico do Hector Quadrotor](http://wiki.ros.org/hector_quadrotor/Tutorials/Quadrotor%20outdoor%20flight%20demo)

## Tutorial

### Pré Requisitos

* ROS instalado e rodando
* Internet
* Ambiente Gráfico (para visualizar a simulação)

### Instalação
1. Instalar os pacotes binários:
```bash
sudo apt-get install ros-indigo-hector-quadrotor-demo
```
*Obs: subtitua 'indigo' pela a versão do ros que você está utilizando*
2. Rodar:
```bash
roslaunch hector_quadrotor_demo outdoor_flight_gazebo.launch
```
3. Controles:

   3.1 Controle de Xbox360:

    ```bash
    roslaunch hector_quadrotor_teleop xbox_controller.launch
    ```

    3.2 Controle via teclado:

    ```bash
    rosrun pr2_teleop teleop_pr2_keyboard
    ```

### Observações
* Este é apenas um guia de leitura rápida e traduzido para o tutorial real do tutorial original, qualquer dúvida ou problema, se refira ao tutorial original, não este.
