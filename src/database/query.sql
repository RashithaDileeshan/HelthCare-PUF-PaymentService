CREATE TABLE `payservice` (
  `paymentId` int NOT NULL AUTO_INCREMENT,
  `patientName` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `paydate` date DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contactNo` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`paymentId`)
);
