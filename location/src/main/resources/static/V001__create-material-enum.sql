DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'material') THEN
        CREATE TYPE material AS ENUM ('Bateria', 'Pilha', 'Ambos');
    END IF;
END$$;