CREATE TYPE civility AS ENUM ('MADAM', 'MISS', 'SIR');

CREATE TABLE requests(
    id UUID PRIMARY KEY,
    project_name VARCHAR(255),
    user_type VARCHAR(255),
    loan_amount DECIMAL(10, 2),
    loan_duration INT,
    monthly_payment DECIMAL(10, 2),
    email VARCHAR(255),
    phone VARCHAR(255),
    civility civility,
    f_name VARCHAR(255),
    l_name VARCHAR(255),
    cin VARCHAR(255),
    birth_date DATE,
    employment_start_date DATE,
    has_credits BOOLEAN
);
