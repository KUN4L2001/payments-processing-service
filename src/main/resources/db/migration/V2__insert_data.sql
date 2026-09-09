INSERT INTO payments.`Payment_Method`
(`ID`, `NAME`, `STATUS`, `CREATION_DATE`)
VALUES (1, 'APM', 1, '2026-07-08 21:29:49.25');

INSERT INTO payments.`Payment_Type`
(`ID`, `TYPE`, `STATUS`, `CREATION_DATE`)
VALUES (1, 'SALE', 1, '2026-07-08 21:30:42.05');

INSERT INTO payments.`Provider`
(`ID`, `PROVIDER_NAME`, `STATUS`, `CREATION_DATE`)
VALUES (1, 'STRIPE', 1, '2026-07-08 21:31:28.08');

INSERT INTO payments.`Transaction_Status`
(`ID`, `NAME`, `STATUS`, `CREATION_DATE`)
VALUES (1, 'CREATED', 1, '2026-07-08 21:33:39.84');

INSERT INTO payments.`Transaction_Status`
(`ID`, `NAME`, `STATUS`, `CREATION_DATE`)
VALUES (2, 'INITIATED', 2, '2026-07-08 21:33:39.84');

INSERT INTO payments.`Transaction_Status`
(`ID`, `NAME`, `STATUS`, `CREATION_DATE`)
VALUES (3, 'PENDING', 3, '2026-07-08 21:33:39.84');

INSERT INTO payments.`Transaction_Status`
(`ID`, `NAME`, `STATUS`, `CREATION_DATE`)
VALUES (4, 'SUCCESS', 4, '2026-07-08 21:33:39.84');

INSERT INTO payments.`Transaction_Status`
(`ID`, `NAME`, `STATUS`, `CREATION_DATE`)
VALUES (5, 'FAILED', 5, '2026-07-08 21:33:39.84');