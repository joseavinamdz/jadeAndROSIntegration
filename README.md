# jadeAndROSIntegration
Projeto para integrar a plataforma de Agentes JADE com a plataforma de robótica ROS
Autor: Pedro Mathias Nakibar

#Observações
Além deste repositório é necessário ter o *ros e o *ros-bridgesuite instalados.
Para usufruir das ferramentas, apenas inicie o rosbridgesuite:
```bash
roslaunch rosbridge_server rosbridge_websocket.launch
```
E o turtlesim node:
```bash
rosrun turtlesim turtlesim_node
```

#Material e documentação
- [Tutorial de como montar o projeto e rodar o exemplo (piloto -> agente tartaruga)](http://pt.slideshare.net/pedronakibar/tutorial-setup-projeto-jade-e-ros)
- [Survey: Integração de sistemas robóticos com programação orientada a agentes](http://www.slideshare.net/pedronakibar/survey-integrao-de-sistemas-robticos-com-programao-orientada-a-agentes)
- [Slides de apoio ao survey](http://pt.slideshare.net/pedronakibar/apresentao-integrao-ros-e-jade)

##Diagramas
###Diagram de sequência:
![Diagrama de sequência](https://raw.githubusercontent.com/pnakibar/jadeAndROSIntegration/master/diagramas/Diagram%20de%20Sequ%C3%AAncia.png)
Como podemos perceber, a mensagem começa no Agente Piloto e vai até chegar ao ROS.

###Diagrama de classes:
![Diagrama de classes](https://raw.githubusercontent.com/pnakibar/jadeAndROSIntegration/master/diagramas/Diagrama%20de%20classes.png)
Todas os agentes herdam da classe Agent do Jade e a classe Tartaruga usa os metódos do pacote jrosbridge.

###Diagrama de implantação:
![Diagrama de implantação](https://raw.githubusercontent.com/pnakibar/jadeAndROSIntegration/master/diagramas/Diagrama%20implanta%C3%A7%C3%A3o.png)
Os nós do sistemas precisam ambos ter o Jade instalado, para criação e movimentação de agentes.

###Diagrama de atividade:
![Diagrama de atividade](https://raw.githubusercontent.com/pnakibar/jadeAndROSIntegration/master/diagramas/Vida%20de%20um%20Agente.png)
Diagrama de atividades exemplificando a vida de um agente no sistema. Do momento em que é criado até o momento em que ele termina de fazer seus deveres e é "morto" pelo ambiente.


