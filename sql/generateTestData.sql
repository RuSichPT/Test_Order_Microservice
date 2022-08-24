-- ORDER, ORDER ITEM
DO $$
DECLARE
v_order_id integer;
v_order_item_id integer;
BEGIN
    SELECT NEXTVAL('order_seq') INTO v_order_id;
    INSERT INTO "order"(ID, ORDER_STATUS_ID, PATIENT_ID, CUSTOMER_COMMENT)
    VALUES (v_order_id, 1, 1, 'Pls. call before delivery');

    SELECT NEXTVAL('order_item_seq') INTO v_order_item_id;
    INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME)
    VALUES (v_order_item_id, v_order_id, 'Order Item #1');

    SELECT NEXTVAL('order_item_seq') INTO v_order_item_id;
    INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME)
    VALUES (v_order_item_id, v_order_id, 'Order Item #2');

    SELECT NEXTVAL('order_item_seq') INTO v_order_item_id;
    INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME)
    VALUES (v_order_item_id, v_order_id, 'Order Item #3');
END $$;

-- SESSION
INSERT INTO "session" (ID, SESSION_ID, START_TIME, TIMEOUT_MINUTES)
VALUES (NEXTVAL('session_seq'), '1234-AAFF-BB55-DD22', '22.08.2022 16:42:00 ', 60);