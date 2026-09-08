-- Create Tables payments Schema Start***
-- OneToMany with Transaction table
CREATE TABLE payments.`Payment_Method` (
 `id` int NOT NULL,
 `name` varchar(500) NOT NULL,
 `status` tinyint DEFAULT 1,
 `creationDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- OneToMany with Transaction table
CREATE TABLE payments.`Payment_Type` (
 `id` int NOT NULL,
 `type` varchar(500) NOT NULL,
 `status` tinyint DEFAULT 1,
 `creationDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- OneToMany with Transaction table
CREATE TABLE payments.`Provider` (
 `id` int NOT NULL,
 `providerName` varchar(2000) NOT NULL,
 `status` tinyint DEFAULT 1,
 `creationDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- OneToMany with Transaction table
CREATE TABLE payments.`Transaction_Status` (
 `id` int NOT NULL,
 `name` varchar(2000) NOT NULL,
 `status` tinyint DEFAULT 1,
 `creationDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Parent table
CREATE TABLE payments.`Transaction` (
 `id` int NOT NULL AUTO_INCREMENT,
 `userId` int NOT NULL,
 `paymentMethodId` int NOT NULL,
 `providerId` int NOT NULL,
 `paymentTypeId` int NOT NULL,
 `txnStatusId` int NOT NULL,
 `amount` decimal(19,2) DEFAULT '0.00',
 `currency` varchar(3) NOT NULL,
 `merchantTransactionReference` varchar(50) NOT NULL,
 `txnReference` varchar(50) NOT NULL,
 `providerReference` varchar(100) DEFAULT NULL,
 `providerCode` varchar(500) DEFAULT NULL,
 `providerMessage` varchar(1000) DEFAULT NULL,
 `creationDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 `updatedDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 `retryCount` int DEFAULT 0,
 PRIMARY KEY (`id`),
 UNIQUE KEY `transaction_txnReference` (`txnReference`),
 KEY `transaction_paymentMethodId` (`paymentMethodId`),
 KEY `transaction_providerId` (`providerId`),
 KEY `transaction_txnStatusId` (`txnStatusId`),
 KEY `transaction_paymentTypeId` (`paymentTypeId`),
 CONSTRAINT `transaction_paymentMethodId` FOREIGN KEY (`paymentMethodId`) REFERENCES `Payment_Method` (`id`),
 CONSTRAINT `transaction_providerId` FOREIGN KEY (`providerId`) REFERENCES `Provider` (`id`),
 CONSTRAINT `transaction_txnStatusId` FOREIGN KEY (`txnStatusId`) REFERENCES `Transaction_Status` (`id`),
 CONSTRAINT `transaction_paymentTypeId` FOREIGN KEY (`paymentTypeId`) REFERENCES `Payment_Type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- OneToMany Transaction -> Transaction_Log
CREATE TABLE payments.`Transaction_Log` (
 `id` int NOT NULL AUTO_INCREMENT,
 `transactionId` int NOT NULL,
 `txnFromStatus` varchar(50) DEFAULT '-1',
 `txnToStatus` varchar(50) DEFAULT '-1',
 `creationDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 PRIMARY KEY (`id`),
 KEY `transaction_log_transactionId` (`transactionId`),
 CONSTRAINT `transaction_log_transactionId` FOREIGN KEY (`transactionId`) REFERENCES `Transaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- Create Tables payments Schema End***