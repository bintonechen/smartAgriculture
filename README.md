# Smart Agriculture System
This project is a Smart Agriculture system designed to monitor soil conditions, manage irrigation, and allow users to request system health checks. 
The system uses gRPC for communication between a server and three devices: a soil sensor, an irrigation system, and a mobile phone. 
The goal is to demonstrate efficient agricultural management through automated systems.

You can interact with the system via a JavaFX GUI.

## Features
- **Soil Monitoring**: Continuously tracks soil moisture, pH, and temperature, providing summaries to the user.
- **Irrigation Management**: Controls irrigation flow based on real-time status updates and server instructions.
- **Health Check**: Allows users to request a health check of the system, receiving results from the server.

## Technologies Used
- **gRPC**: For communication between devices and the server.
- **JavaFX**: For the GUI, enabling interaction with the system.
- **Consul**: For service discovery and registration.

## Service Discovery
Services are registered with a unique name and ID in **Consul**, allowing easy discovery and communication between different system components.

## GUI
The JavaFX GUI allows users to:
- Log in with a user ID.
- Start soil monitoring and manage irrigation.
- Request system health checks.

*Smart Agriculture application GUI*
<img width="379" alt="image" src="https://github.com/user-attachments/assets/be1affb0-6593-4b2b-99cf-80e4567b52bd" />




  
