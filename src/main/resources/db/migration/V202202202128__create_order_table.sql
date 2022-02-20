CREATE TABLE "public"."orders"
(
    "id" int8 NOT NULL,
    "transaction" int8 NOT NULL,
    "goods" int8 NOT NULL,
    "sku"  varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "count" int8 NOT NULL,
    "amount" int8 NOT NULL,
    "total" int8 NOT NULL,
    "address" jsonb,
    "status"     varchar(255) COLLATE "pg_catalog"."default",
    "updated_at" timestamp(6),
    "created_at" timestamp(6)
);
COMMENT ON COLUMN "public"."orders"."id" IS '主键ID';
COMMENT ON COLUMN "public"."orders"."transaction" IS '订单号';
COMMENT ON COLUMN "public"."orders"."goods" IS '商品ID';
COMMENT ON COLUMN "public"."orders"."sku" IS '商品单位';
COMMENT ON COLUMN "public"."orders"."count" IS '商品数量';
COMMENT ON COLUMN "public"."orders"."amount" IS '商品价格';
COMMENT ON COLUMN "public"."orders"."total" IS '订单总价';
COMMENT ON COLUMN "public"."orders"."address" IS '邮寄地址';
COMMENT ON COLUMN "public"."orders"."status" IS '订单状态';
COMMENT ON COLUMN "public"."orders"."updated_at" IS '更新时间';
COMMENT ON COLUMN "public"."orders"."updated_at" IS '创建时间';

-- ----------------------------
-- Indexes structure for table orders
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table orders
-- ----------------------------
ALTER TABLE "public"."orders"
    ADD CONSTRAINT "orders_pkey" PRIMARY KEY ("id");
