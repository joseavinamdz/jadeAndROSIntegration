# POSIÇÃO DENTRO DO GAZEBO
* Está no topico *fix*:

        rostopic echo fix

* Se tem acesso a: **latitude, longitude, altitude** dentro do simulador


# MOVIMENTAÇÃO
* mais informações em: http://wiki.ros.org/hector_quadrotor_gazebo_plugins

* exemplo de publicação em tópico: rostopic pub [topic] [msg_type] [args]
  * A mensagem tem *Twist* a seguinte forma:
    ```
    geometry_msgs/Vector3 linear
        float64 x
        float64 y
        float64 z
    geometry_msgs/Vector3 angular
        float64 x
        float64 y
        float64 z
    ```
    * exemplo de publicação:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[2.0, 0.0, 0.0]' '[0.0, 0.0, 1.8]'

    * Ir para frente:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[2.0, 0.0, 0.0]' '[0.0, 0.0, 0.0]'

    * Ir para tras:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[-2.0, 0.0, 0.0]' '[0.0, 0.0, 0.0]'

    * Ir para a esquerda:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[0.0, 2.0, 0.0]' '[0.0, 0.0, 0.0]'

    * Ir para a esquerda:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[0.0, -2.0, 0.0]' '[0.0, 0.0, 0.0]'

    * Ir para baixo:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[0.0, 0.0, -2.0]' '[0.0, 0.0, 0.0]'

    * Ir para cima:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[0.0, 0.0, 2.0]' '[0.0, 0.0, 0.0]'

    > obs: *velocidade angular* (segundo argumento) é ignorado nos parâmetros *x* e *y* (primeira e segunda posição do vetor)

    * girar para a esquerda:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[0.0, 0.0, 0.0]' '[0.0, 0.0, 2.0]'

    * girar para a direita:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[0.0, 0.0, 0.0]' '[0.0, 0.0, -2.0]'

    * Parar qualquer movimentação:

            rostopic pub -1 cmd_vel geometry_msgs/Twist -- '[0.0, 0.0, 0.0]' '[0.0, 0.0, 0.0]'
