CREATE TABLE "public"."goods"
(
    "id" int8 NOT NULL,
    "sku"        varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "name"       varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "image"      varchar(255) COLLATE "pg_catalog"."default",
    "amount" int8 NOT NULL,
    "comment"    varchar(255) COLLATE "pg_catalog"."default",
    "updated_at" timestamp(6),
    "created_at" timestamp(6)
);
COMMENT ON COLUMN "public"."goods"."id" IS '主键ID';
COMMENT ON COLUMN "public"."goods"."sku" IS '单位';
COMMENT ON COLUMN "public"."goods"."name" IS '商品名称';
COMMENT ON COLUMN "public"."goods"."image" IS '图片';
COMMENT ON COLUMN "public"."goods"."amount" IS '单价';
COMMENT ON COLUMN "public"."goods"."comment" IS '描述';
COMMENT ON COLUMN "public"."goods"."updated_at" IS '更新时间';
COMMENT ON COLUMN "public"."goods"."created_at" IS '创建时间';

-- ----------------------------
-- Indexes structure for table goods
-- ----------------------------
CREATE INDEX "goods_name_idx_copy" ON "public"."goods" USING btree (
    "name" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
    );
-- ----------------------------
-- Primary Key structure for table goods
-- ----------------------------
ALTER TABLE "public"."goods"
    ADD CONSTRAINT "goods_pkey" PRIMARY KEY ("id");
