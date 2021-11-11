# Book-Store-App-Java-RMI

A book store register system that works as a distributed object-based application using Java RMI. The application idea is based on and extended from Client-Server-Application repo. The Java RMI remote interface implemented provides 5 unique remote methods (services) to clients. Clients can order books by selecting the book store they want to order books from and start by typing in the name and quantity of the book they'd like to purchase. Clients can also ask the server to recommend a top selling book from a selected store if they can’t choose on their own. Clients can ask to view the total cost of their orders or view the receipt for the day.

## Usage

Follow the instructions below to run the project on your local machine for development and testing purposes. 

- Clone project and run in cmd

git clone https://github.com/esam191/Book-Store-App-Java-RMI

- Start the rmiregistry

```
Usage: start rmiregistry
```

- Start the Server

```
Usage: java BookStoreServer
```

- Start the Client

```
Usage: java BookStoreClient <host name>
```

```
Please select one of the options: 1 - Get Book File, 2 - Give New Book Order, 
```
```
3 - View Total Cost, 4 - Best Seller Recommendation, 5 - View Receipt, -1 - exit
```
