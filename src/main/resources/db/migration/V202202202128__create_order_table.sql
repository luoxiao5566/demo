CREATE TABLE "public"."order"
(
    "id" int8 NOT NULL,
    "order_id" int8 NOT NULL,
    "goods_sku"  varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "count" int8 NOT NULL,
    "amount" int8 NOT NULL,
    "total_price" int8 NOT NULL,
    "address" jsonb,
    "status"     varchar(255) COLLATE "pg_catalog"."default",
    "updated_at" timestamp(6),
    "created_at" timestamp(6)
);
COMMENT ON COLUMN "public"."order"."id" IS '主键ID';
COMMENT ON COLUMN "public"."order"."order_id" IS '订单ID';
COMMENT ON COLUMN "public"."order"."goods_sku" IS '商品单位';
COMMENT ON COLUMN "public"."order"."count" IS '商品数量';
COMMENT ON COLUMN "public"."order"."amount" IS '商品价格';
COMMENT ON COLUMN "public"."order"."total_price" IS '订单总价';
COMMENT ON COLUMN "public"."order"."address" IS '邮寄地址';
COMMENT ON COLUMN "public"."order"."status" IS '订单状态';
COMMENT ON COLUMN "public"."order"."updated_at" IS '更新时间';
COMMENT ON COLUMN "public"."order"."created_at" IS '创建时间';

-- ----------------------------
-- Indexes structure for table order
-- ----------------------------
CREATE INDEX "order_order_id_idx_copy" ON "public"."order" USING btree (
    "order_id" "pg_catalog"."int8_ops" ASC NULLS LAST
    );
-- ----------------------------
-- Primary Key structure for table order
-- ----------------------------
ALTER TABLE "public"."order"
    ADD CONSTRAINT "order_pkey" PRIMARY KEY ("id");
