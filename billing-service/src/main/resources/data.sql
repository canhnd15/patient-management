-- Ensure the 'patient' table exists
CREATE TABLE IF NOT EXISTS billing
(
    id              UUID PRIMARY KEY,
    patient_id      UUID                NOT NULL,
    title           VARCHAR(255)        NOT NULL,
    email           VARCHAR(255)        NOT NULL,
    status          VARCHAR(255)        NOT NULL,
    total_amount    INT                 NOT NULL,
    discount        INT                 NOT NULL,
    created_date    DATE                NOT NULL,
    expire_date     DATE                NOT NULL
);

-- Insert well-known UUIDs for specific patients
INSERT INTO billing (id, patient_id, title, email, status, total_amount, discount, created_date, expire_date)
SELECT 'b1111111-aaaa-bbbb-cccc-000000000001',
       '123e4567-e89b-12d3-a456-426614174000',
       'General Checkup Invoice',
       'test1@example.com',
       'PAID',
       200000,
       10000,
       '2024-01-11',
       '2024-02-11'
    WHERE NOT EXISTS (
    SELECT 1 FROM billing WHERE id = 'b1111111-aaaa-bbbb-cccc-000000000001'
);

INSERT INTO billing (id, patient_id, title, email, status, total_amount, discount, created_date, expire_date)
SELECT 'b1111111-aaaa-bbbb-cccc-000000000002',
       '123e4567-e89b-12d3-a456-426614174001',
       'Dental Service Invoice',
       'test2@example.com',
       'PAID',
       500000,
       50000,
       '2024-02-15',
       '2024-03-15'
    WHERE NOT EXISTS (
    SELECT 1 FROM billing WHERE id = 'b1111111-aaaa-bbbb-cccc-000000000002'
);