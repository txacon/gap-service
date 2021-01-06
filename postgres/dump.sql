--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1 (Debian 13.1-1.pgdg100+1)
-- Dumped by pg_dump version 13.1 (Ubuntu 13.1-1.pgdg20.04+1)

-- Started on 2021-01-06 20:23:33 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

-- CREATE SCHEMA public;


--
-- TOC entry 3207 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 16554)
-- Name: address; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.address (
    address_id bigint NOT NULL,
    create_at timestamp without time zone,
    last_modified timestamp without time zone,
    city character varying(255),
    country character varying(255),
    state character varying(255),
    street1 character varying(255),
    street2 character varying(255),
    zipcode integer,
    customer_id bigint NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 16552)
-- Name: address_address_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.address_address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3208 (class 0 OID 0)
-- Dependencies: 219
-- Name: address_address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.address_address_id_seq OWNED BY public.address.address_id;


--
-- TOC entry 221 (class 1259 OID 16563)
-- Name: aggregate_rating; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.aggregate_rating (
    aggregate_rating_id character varying(255) NOT NULL,
    create_at timestamp without time zone,
    last_modified timestamp without time zone
);


--
-- TOC entry 207 (class 1259 OID 16416)
-- Name: auth_group; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.auth_group (
    id integer NOT NULL,
    name character varying(150) NOT NULL
);


--
-- TOC entry 206 (class 1259 OID 16414)
-- Name: auth_group_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.auth_group_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3209 (class 0 OID 0)
-- Dependencies: 206
-- Name: auth_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.auth_group_id_seq OWNED BY public.auth_group.id;


--
-- TOC entry 209 (class 1259 OID 16426)
-- Name: auth_group_permissions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.auth_group_permissions (
    id integer NOT NULL,
    group_id integer NOT NULL,
    permission_id integer NOT NULL
);


--
-- TOC entry 208 (class 1259 OID 16424)
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.auth_group_permissions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3210 (class 0 OID 0)
-- Dependencies: 208
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.auth_group_permissions_id_seq OWNED BY public.auth_group_permissions.id;


--
-- TOC entry 205 (class 1259 OID 16408)
-- Name: auth_permission; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.auth_permission (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    content_type_id integer NOT NULL,
    codename character varying(100) NOT NULL
);


--
-- TOC entry 204 (class 1259 OID 16406)
-- Name: auth_permission_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.auth_permission_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3211 (class 0 OID 0)
-- Dependencies: 204
-- Name: auth_permission_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.auth_permission_id_seq OWNED BY public.auth_permission.id;


--
-- TOC entry 223 (class 1259 OID 16570)
-- Name: business; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.business (
    business_id bigint NOT NULL,
    create_at timestamp without time zone,
    last_modified timestamp without time zone,
    active boolean NOT NULL,
    city character varying(255),
    close_hour time without time zone,
    country character varying(255),
    description character varying(1000),
    email character varying(255),
    fiscal_id character varying(255),
    name character varying(255),
    open_hour time without time zone,
    phone character varying(255),
    phone_prefix character varying(255),
    state character varying(255),
    street1 character varying(255),
    street2 character varying(255),
    zipcode character varying(255),
    aggregate_rating_id character varying(255),
    customer_id bigint NOT NULL,
    price_range_id character varying(255)
);


--
-- TOC entry 222 (class 1259 OID 16568)
-- Name: business_business_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.business_business_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3212 (class 0 OID 0)
-- Dependencies: 222
-- Name: business_business_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.business_business_id_seq OWNED BY public.business.business_id;


--
-- TOC entry 224 (class 1259 OID 16579)
-- Name: business_payment_method; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.business_payment_method (
    business_id bigint NOT NULL,
    payment_method_id character varying(255) NOT NULL
);


--
-- TOC entry 211 (class 1259 OID 16460)
-- Name: customer; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customer (
    last_login timestamp with time zone,
    customer_id bigint NOT NULL,
    create_at timestamp with time zone,
    last_modified timestamp with time zone,
    is_active boolean NOT NULL,
    is_staff boolean NOT NULL,
    is_superuser boolean NOT NULL,
    email character varying(255),
    last_name character varying(255),
    first_name character varying(255),
    username character varying(255),
    password character varying(255)
);


--
-- TOC entry 210 (class 1259 OID 16458)
-- Name: customer_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.customer_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3213 (class 0 OID 0)
-- Dependencies: 210
-- Name: customer_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.customer_customer_id_seq OWNED BY public.customer.customer_id;


--
-- TOC entry 213 (class 1259 OID 16473)
-- Name: customer_groups; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customer_groups (
    id integer NOT NULL,
    customer_id bigint NOT NULL,
    group_id integer NOT NULL
);


--
-- TOC entry 212 (class 1259 OID 16471)
-- Name: customer_groups_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.customer_groups_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3214 (class 0 OID 0)
-- Dependencies: 212
-- Name: customer_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.customer_groups_id_seq OWNED BY public.customer_groups.id;


--
-- TOC entry 225 (class 1259 OID 16584)
-- Name: customer_role; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customer_role (
    customer_id bigint NOT NULL,
    role_id bigint NOT NULL
);


--
-- TOC entry 215 (class 1259 OID 16481)
-- Name: customer_user_permissions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customer_user_permissions (
    id integer NOT NULL,
    customer_id bigint NOT NULL,
    permission_id integer NOT NULL
);


--
-- TOC entry 214 (class 1259 OID 16479)
-- Name: customer_user_permissions_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.customer_user_permissions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3215 (class 0 OID 0)
-- Dependencies: 214
-- Name: customer_user_permissions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.customer_user_permissions_id_seq OWNED BY public.customer_user_permissions.id;


--
-- TOC entry 217 (class 1259 OID 16520)
-- Name: django_admin_log; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.django_admin_log (
    id integer NOT NULL,
    action_time timestamp with time zone NOT NULL,
    object_id text,
    object_repr character varying(200) NOT NULL,
    action_flag smallint NOT NULL,
    change_message text NOT NULL,
    content_type_id integer,
    user_id bigint NOT NULL,
    CONSTRAINT django_admin_log_action_flag_check CHECK ((action_flag >= 0))
);


--
-- TOC entry 216 (class 1259 OID 16518)
-- Name: django_admin_log_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.django_admin_log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3216 (class 0 OID 0)
-- Dependencies: 216
-- Name: django_admin_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.django_admin_log_id_seq OWNED BY public.django_admin_log.id;


--
-- TOC entry 203 (class 1259 OID 16398)
-- Name: django_content_type; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.django_content_type (
    id integer NOT NULL,
    app_label character varying(100) NOT NULL,
    model character varying(100) NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 16396)
-- Name: django_content_type_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.django_content_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3217 (class 0 OID 0)
-- Dependencies: 202
-- Name: django_content_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.django_content_type_id_seq OWNED BY public.django_content_type.id;


--
-- TOC entry 201 (class 1259 OID 16387)
-- Name: django_migrations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.django_migrations (
    id integer NOT NULL,
    app character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    applied timestamp with time zone NOT NULL
);


--
-- TOC entry 200 (class 1259 OID 16385)
-- Name: django_migrations_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.django_migrations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3218 (class 0 OID 0)
-- Dependencies: 200
-- Name: django_migrations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.django_migrations_id_seq OWNED BY public.django_migrations.id;


--
-- TOC entry 218 (class 1259 OID 16542)
-- Name: django_session; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.django_session (
    session_key character varying(40) NOT NULL,
    session_data text NOT NULL,
    expire_date timestamp with time zone NOT NULL
);


--
-- TOC entry 226 (class 1259 OID 16589)
-- Name: payment_method; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.payment_method (
    payment_method_id character varying(255) NOT NULL,
    create_at timestamp without time zone,
    last_modified timestamp without time zone
);


--
-- TOC entry 228 (class 1259 OID 16596)
-- Name: phone; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.phone (
    phone_id bigint NOT NULL,
    create_at timestamp without time zone,
    last_modified timestamp without time zone,
    country_prefix character varying(255),
    phone_number character varying(255),
    customer_id bigint NOT NULL
);


--
-- TOC entry 227 (class 1259 OID 16594)
-- Name: phone_phone_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.phone_phone_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3219 (class 0 OID 0)
-- Dependencies: 227
-- Name: phone_phone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.phone_phone_id_seq OWNED BY public.phone.phone_id;


--
-- TOC entry 229 (class 1259 OID 16605)
-- Name: price_range; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.price_range (
    price_range_id character varying(255) NOT NULL,
    create_at timestamp without time zone,
    last_modified timestamp without time zone
);


--
-- TOC entry 231 (class 1259 OID 16612)
-- Name: product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product (
    product_id bigint NOT NULL,
    create_at timestamp without time zone,
    last_modified timestamp without time zone,
    active boolean NOT NULL,
    description character varying(1000),
    name character varying(255),
    photo_link character varying(255),
    retail_price numeric(19,2),
    whole_sale_price numeric(19,2),
    business_id bigint NOT NULL
);


--
-- TOC entry 230 (class 1259 OID 16610)
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3220 (class 0 OID 0)
-- Dependencies: 230
-- Name: product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_product_id_seq OWNED BY public.product.product_id;


--
-- TOC entry 232 (class 1259 OID 16621)
-- Name: product_tags; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product_tags (
    tag_id bigint NOT NULL,
    product_id character varying(255) NOT NULL
);


--
-- TOC entry 234 (class 1259 OID 16626)
-- Name: role; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.role (
    role_id bigint NOT NULL,
    create_at timestamp without time zone,
    last_modified timestamp without time zone,
    name character varying(255)
);


--
-- TOC entry 233 (class 1259 OID 16624)
-- Name: role_role_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3221 (class 0 OID 0)
-- Dependencies: 233
-- Name: role_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.role_role_id_seq OWNED BY public.role.role_id;


--
-- TOC entry 235 (class 1259 OID 16632)
-- Name: tag; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tag (
    tag_id character varying(255) NOT NULL,
    create_at timestamp without time zone,
    last_modified timestamp without time zone
);


--
-- TOC entry 2931 (class 2604 OID 16557)
-- Name: address address_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.address ALTER COLUMN address_id SET DEFAULT nextval('public.address_address_id_seq'::regclass);


--
-- TOC entry 2924 (class 2604 OID 16419)
-- Name: auth_group id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_group ALTER COLUMN id SET DEFAULT nextval('public.auth_group_id_seq'::regclass);


--
-- TOC entry 2925 (class 2604 OID 16429)
-- Name: auth_group_permissions id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_group_permissions ALTER COLUMN id SET DEFAULT nextval('public.auth_group_permissions_id_seq'::regclass);


--
-- TOC entry 2923 (class 2604 OID 16411)
-- Name: auth_permission id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_permission ALTER COLUMN id SET DEFAULT nextval('public.auth_permission_id_seq'::regclass);


--
-- TOC entry 2932 (class 2604 OID 16573)
-- Name: business business_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.business ALTER COLUMN business_id SET DEFAULT nextval('public.business_business_id_seq'::regclass);


--
-- TOC entry 2926 (class 2604 OID 16463)
-- Name: customer customer_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer ALTER COLUMN customer_id SET DEFAULT nextval('public.customer_customer_id_seq'::regclass);


--
-- TOC entry 2927 (class 2604 OID 16476)
-- Name: customer_groups id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_groups ALTER COLUMN id SET DEFAULT nextval('public.customer_groups_id_seq'::regclass);


--
-- TOC entry 2928 (class 2604 OID 16484)
-- Name: customer_user_permissions id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_user_permissions ALTER COLUMN id SET DEFAULT nextval('public.customer_user_permissions_id_seq'::regclass);


--
-- TOC entry 2929 (class 2604 OID 16523)
-- Name: django_admin_log id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_admin_log ALTER COLUMN id SET DEFAULT nextval('public.django_admin_log_id_seq'::regclass);


--
-- TOC entry 2922 (class 2604 OID 16401)
-- Name: django_content_type id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_content_type ALTER COLUMN id SET DEFAULT nextval('public.django_content_type_id_seq'::regclass);


--
-- TOC entry 2921 (class 2604 OID 16390)
-- Name: django_migrations id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_migrations ALTER COLUMN id SET DEFAULT nextval('public.django_migrations_id_seq'::regclass);


--
-- TOC entry 2933 (class 2604 OID 16599)
-- Name: phone phone_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.phone ALTER COLUMN phone_id SET DEFAULT nextval('public.phone_phone_id_seq'::regclass);


--
-- TOC entry 2934 (class 2604 OID 16615)
-- Name: product product_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product ALTER COLUMN product_id SET DEFAULT nextval('public.product_product_id_seq'::regclass);


--
-- TOC entry 2935 (class 2604 OID 16629)
-- Name: role role_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role ALTER COLUMN role_id SET DEFAULT nextval('public.role_role_id_seq'::regclass);


--
-- TOC entry 3186 (class 0 OID 16554)
-- Dependencies: 220
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3187 (class 0 OID 16563)
-- Dependencies: 221
-- Data for Name: aggregate_rating; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.aggregate_rating VALUES ('ONE_START', '2021-01-06 18:44:26.956', '2021-01-06 18:44:26.956');
INSERT INTO public.aggregate_rating VALUES ('TWO_STARTS', '2021-01-06 18:44:26.966', '2021-01-06 18:44:26.966');
INSERT INTO public.aggregate_rating VALUES ('THREE_STARTS', '2021-01-06 18:44:26.972', '2021-01-06 18:44:26.972');
INSERT INTO public.aggregate_rating VALUES ('FOUR_STARS', '2021-01-06 18:44:26.978', '2021-01-06 18:44:26.978');
INSERT INTO public.aggregate_rating VALUES ('FIVE_START', '2021-01-06 18:44:26.985', '2021-01-06 18:44:26.985');


--
-- TOC entry 3173 (class 0 OID 16416)
-- Dependencies: 207
-- Data for Name: auth_group; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.auth_group VALUES (1, 'User');


--
-- TOC entry 3175 (class 0 OID 16426)
-- Dependencies: 209
-- Data for Name: auth_group_permissions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.auth_group_permissions VALUES (1, 1, 32);
INSERT INTO public.auth_group_permissions VALUES (2, 1, 64);
INSERT INTO public.auth_group_permissions VALUES (3, 1, 4);
INSERT INTO public.auth_group_permissions VALUES (4, 1, 68);
INSERT INTO public.auth_group_permissions VALUES (5, 1, 36);
INSERT INTO public.auth_group_permissions VALUES (6, 1, 8);
INSERT INTO public.auth_group_permissions VALUES (7, 1, 72);
INSERT INTO public.auth_group_permissions VALUES (8, 1, 40);
INSERT INTO public.auth_group_permissions VALUES (9, 1, 12);
INSERT INTO public.auth_group_permissions VALUES (10, 1, 44);
INSERT INTO public.auth_group_permissions VALUES (11, 1, 60);
INSERT INTO public.auth_group_permissions VALUES (12, 1, 48);
INSERT INTO public.auth_group_permissions VALUES (13, 1, 16);
INSERT INTO public.auth_group_permissions VALUES (14, 1, 52);
INSERT INTO public.auth_group_permissions VALUES (15, 1, 20);
INSERT INTO public.auth_group_permissions VALUES (16, 1, 24);
INSERT INTO public.auth_group_permissions VALUES (17, 1, 56);
INSERT INTO public.auth_group_permissions VALUES (18, 1, 28);


--
-- TOC entry 3171 (class 0 OID 16408)
-- Dependencies: 205
-- Data for Name: auth_permission; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.auth_permission VALUES (1, 'Can add log entry', 1, 'add_logentry');
INSERT INTO public.auth_permission VALUES (2, 'Can change log entry', 1, 'change_logentry');
INSERT INTO public.auth_permission VALUES (3, 'Can delete log entry', 1, 'delete_logentry');
INSERT INTO public.auth_permission VALUES (4, 'Can view log entry', 1, 'view_logentry');
INSERT INTO public.auth_permission VALUES (5, 'Can add permission', 2, 'add_permission');
INSERT INTO public.auth_permission VALUES (6, 'Can change permission', 2, 'change_permission');
INSERT INTO public.auth_permission VALUES (7, 'Can delete permission', 2, 'delete_permission');
INSERT INTO public.auth_permission VALUES (8, 'Can view permission', 2, 'view_permission');
INSERT INTO public.auth_permission VALUES (9, 'Can add group', 3, 'add_group');
INSERT INTO public.auth_permission VALUES (10, 'Can change group', 3, 'change_group');
INSERT INTO public.auth_permission VALUES (11, 'Can delete group', 3, 'delete_group');
INSERT INTO public.auth_permission VALUES (12, 'Can view group', 3, 'view_group');
INSERT INTO public.auth_permission VALUES (13, 'Can add content type', 4, 'add_contenttype');
INSERT INTO public.auth_permission VALUES (14, 'Can change content type', 4, 'change_contenttype');
INSERT INTO public.auth_permission VALUES (15, 'Can delete content type', 4, 'delete_contenttype');
INSERT INTO public.auth_permission VALUES (16, 'Can view content type', 4, 'view_contenttype');
INSERT INTO public.auth_permission VALUES (17, 'Can add session', 5, 'add_session');
INSERT INTO public.auth_permission VALUES (18, 'Can change session', 5, 'change_session');
INSERT INTO public.auth_permission VALUES (19, 'Can delete session', 5, 'delete_session');
INSERT INTO public.auth_permission VALUES (20, 'Can view session', 5, 'view_session');
INSERT INTO public.auth_permission VALUES (21, 'Can add address', 6, 'add_address');
INSERT INTO public.auth_permission VALUES (22, 'Can change address', 6, 'change_address');
INSERT INTO public.auth_permission VALUES (23, 'Can delete address', 6, 'delete_address');
INSERT INTO public.auth_permission VALUES (24, 'Can view address', 6, 'view_address');
INSERT INTO public.auth_permission VALUES (25, 'Can add aggregate rating', 7, 'add_aggregaterating');
INSERT INTO public.auth_permission VALUES (26, 'Can change aggregate rating', 7, 'change_aggregaterating');
INSERT INTO public.auth_permission VALUES (27, 'Can delete aggregate rating', 7, 'delete_aggregaterating');
INSERT INTO public.auth_permission VALUES (28, 'Can view aggregate rating', 7, 'view_aggregaterating');
INSERT INTO public.auth_permission VALUES (29, 'Can add business', 8, 'add_business');
INSERT INTO public.auth_permission VALUES (30, 'Can change business', 8, 'change_business');
INSERT INTO public.auth_permission VALUES (31, 'Can delete business', 8, 'delete_business');
INSERT INTO public.auth_permission VALUES (32, 'Can view business', 8, 'view_business');
INSERT INTO public.auth_permission VALUES (33, 'Can add payment method', 9, 'add_paymentmethod');
INSERT INTO public.auth_permission VALUES (34, 'Can change payment method', 9, 'change_paymentmethod');
INSERT INTO public.auth_permission VALUES (35, 'Can delete payment method', 9, 'delete_paymentmethod');
INSERT INTO public.auth_permission VALUES (36, 'Can view payment method', 9, 'view_paymentmethod');
INSERT INTO public.auth_permission VALUES (37, 'Can add phone', 10, 'add_phone');
INSERT INTO public.auth_permission VALUES (38, 'Can change phone', 10, 'change_phone');
INSERT INTO public.auth_permission VALUES (39, 'Can delete phone', 10, 'delete_phone');
INSERT INTO public.auth_permission VALUES (40, 'Can view phone', 10, 'view_phone');
INSERT INTO public.auth_permission VALUES (41, 'Can add price range', 11, 'add_pricerange');
INSERT INTO public.auth_permission VALUES (42, 'Can change price range', 11, 'change_pricerange');
INSERT INTO public.auth_permission VALUES (43, 'Can delete price range', 11, 'delete_pricerange');
INSERT INTO public.auth_permission VALUES (44, 'Can view price range', 11, 'view_pricerange');
INSERT INTO public.auth_permission VALUES (45, 'Can add product', 12, 'add_product');
INSERT INTO public.auth_permission VALUES (46, 'Can change product', 12, 'change_product');
INSERT INTO public.auth_permission VALUES (47, 'Can delete product', 12, 'delete_product');
INSERT INTO public.auth_permission VALUES (48, 'Can view product', 12, 'view_product');
INSERT INTO public.auth_permission VALUES (49, 'Can add product tags', 13, 'add_producttags');
INSERT INTO public.auth_permission VALUES (50, 'Can change product tags', 13, 'change_producttags');
INSERT INTO public.auth_permission VALUES (51, 'Can delete product tags', 13, 'delete_producttags');
INSERT INTO public.auth_permission VALUES (52, 'Can view product tags', 13, 'view_producttags');
INSERT INTO public.auth_permission VALUES (53, 'Can add role', 14, 'add_role');
INSERT INTO public.auth_permission VALUES (54, 'Can change role', 14, 'change_role');
INSERT INTO public.auth_permission VALUES (55, 'Can delete role', 14, 'delete_role');
INSERT INTO public.auth_permission VALUES (56, 'Can view role', 14, 'view_role');
INSERT INTO public.auth_permission VALUES (57, 'Can add tag', 15, 'add_tag');
INSERT INTO public.auth_permission VALUES (58, 'Can change tag', 15, 'change_tag');
INSERT INTO public.auth_permission VALUES (59, 'Can delete tag', 15, 'delete_tag');
INSERT INTO public.auth_permission VALUES (60, 'Can view tag', 15, 'view_tag');
INSERT INTO public.auth_permission VALUES (61, 'Can add customer', 16, 'add_customer');
INSERT INTO public.auth_permission VALUES (62, 'Can change customer', 16, 'change_customer');
INSERT INTO public.auth_permission VALUES (63, 'Can delete customer', 16, 'delete_customer');
INSERT INTO public.auth_permission VALUES (64, 'Can view customer', 16, 'view_customer');
INSERT INTO public.auth_permission VALUES (65, 'Can add business payment method', 17, 'add_businesspaymentmethod');
INSERT INTO public.auth_permission VALUES (66, 'Can change business payment method', 17, 'change_businesspaymentmethod');
INSERT INTO public.auth_permission VALUES (67, 'Can delete business payment method', 17, 'delete_businesspaymentmethod');
INSERT INTO public.auth_permission VALUES (68, 'Can view business payment method', 17, 'view_businesspaymentmethod');
INSERT INTO public.auth_permission VALUES (69, 'Can add customer role', 18, 'add_customerrole');
INSERT INTO public.auth_permission VALUES (70, 'Can change customer role', 18, 'change_customerrole');
INSERT INTO public.auth_permission VALUES (71, 'Can delete customer role', 18, 'delete_customerrole');
INSERT INTO public.auth_permission VALUES (72, 'Can view customer role', 18, 'view_customerrole');


--
-- TOC entry 3189 (class 0 OID 16570)
-- Dependencies: 223
-- Data for Name: business; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.business VALUES (1, NULL, '2021-01-06 18:52:33.017', true, 'Madrid', NULL, 'Spain', 'En La Tagliatella podrás degustar gran variedad de comida italiana, disfrutando de la gastronomía de Italia en pleno centro de la ciudad', 'pedidos@taggliatella.com', '2342334234', 'La Taggliatella', NULL, '78289292', NULL, 'Madrid', 'Calle Napoles', 'N 2', '28080', NULL, 1, NULL);
INSERT INTO public.business VALUES (2, NULL, '2021-01-06 18:56:06.925', true, 'Madrid', NULL, 'Spain', 'Especialidad en Carnes de aves de corral', 'contactopedidos@elgallo.com', '1234982', 'Restaurante El Gallo', NULL, '272798292', NULL, 'Madrid', 'Calle Mayor 2', '', '28833', NULL, 1, NULL);
INSERT INTO public.business VALUES (4, NULL, '2021-01-06 19:20:45.468', true, 'Madrid', NULL, 'Spain', 'Restaurante Don Pollo', 'pedidos@donpollo.com', '23298733', 'DonPollo', NULL, '89238338', NULL, 'Madrid', 'Calle la codorniz', 'N1', '28373', NULL, 2, NULL);


--
-- TOC entry 3190 (class 0 OID 16579)
-- Dependencies: 224
-- Data for Name: business_payment_method; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3177 (class 0 OID 16460)
-- Dependencies: 211
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.customer VALUES ('2021-01-06 19:16:40.580097+00', 2, NULL, NULL, true, true, true, 'admin@test.com', 'LastName', 'FirstName', 'admin', 'bcrypt$$2b$12$KiBJbI2S3jgfoFnpurvHbuGFm/JWAD2/v76cTVu2T/ECMcy5VVtY2');
INSERT INTO public.customer VALUES ('2021-01-06 19:16:08+00', 1, '2021-01-06 18:44:27+00', '2021-01-06 18:44:27+00', true, false, false, 'user@test.com', 'LastName', 'FirstName', 'User', 'bcrypt$$2b$12$tPymzhn5r5oz.JR3ZIHqieOmIX5CdR2Xqsdl51Kr92AI8tjgJWOOW');


--
-- TOC entry 3179 (class 0 OID 16473)
-- Dependencies: 213
-- Data for Name: customer_groups; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.customer_groups VALUES (1, 1, 1);


--
-- TOC entry 3191 (class 0 OID 16584)
-- Dependencies: 225
-- Data for Name: customer_role; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.customer_role VALUES (1, 2);
INSERT INTO public.customer_role VALUES (2, 1);


--
-- TOC entry 3181 (class 0 OID 16481)
-- Dependencies: 215
-- Data for Name: customer_user_permissions; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3183 (class 0 OID 16520)
-- Dependencies: 217
-- Data for Name: django_admin_log; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.django_admin_log VALUES (1, '2021-01-06 18:59:30.354705+00', '2', 'admin@test.com ROLE_ADMIN', 1, '[{"added": {}}]', 18, 2);
INSERT INTO public.django_admin_log VALUES (2, '2021-01-06 19:00:31.367455+00', '1', 'user@test.com', 2, '[{"changed": {"fields": ["Is staff"]}}]', 16, 2);
INSERT INTO public.django_admin_log VALUES (3, '2021-01-06 19:01:59.533908+00', '1', 'User', 1, '[{"added": {}}]', 3, 2);
INSERT INTO public.django_admin_log VALUES (4, '2021-01-06 19:16:50.312073+00', '1', 'user@test.com', 2, '[{"changed": {"fields": ["Is staff"]}}]', 16, 2);


--
-- TOC entry 3169 (class 0 OID 16398)
-- Dependencies: 203
-- Data for Name: django_content_type; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.django_content_type VALUES (1, 'admin', 'logentry');
INSERT INTO public.django_content_type VALUES (2, 'auth', 'permission');
INSERT INTO public.django_content_type VALUES (3, 'auth', 'group');
INSERT INTO public.django_content_type VALUES (4, 'contenttypes', 'contenttype');
INSERT INTO public.django_content_type VALUES (5, 'sessions', 'session');
INSERT INTO public.django_content_type VALUES (6, 'backadmin', 'address');
INSERT INTO public.django_content_type VALUES (7, 'backadmin', 'aggregaterating');
INSERT INTO public.django_content_type VALUES (8, 'backadmin', 'business');
INSERT INTO public.django_content_type VALUES (9, 'backadmin', 'paymentmethod');
INSERT INTO public.django_content_type VALUES (10, 'backadmin', 'phone');
INSERT INTO public.django_content_type VALUES (11, 'backadmin', 'pricerange');
INSERT INTO public.django_content_type VALUES (12, 'backadmin', 'product');
INSERT INTO public.django_content_type VALUES (13, 'backadmin', 'producttags');
INSERT INTO public.django_content_type VALUES (14, 'backadmin', 'role');
INSERT INTO public.django_content_type VALUES (15, 'backadmin', 'tag');
INSERT INTO public.django_content_type VALUES (16, 'backadmin', 'customer');
INSERT INTO public.django_content_type VALUES (17, 'backadmin', 'businesspaymentmethod');
INSERT INTO public.django_content_type VALUES (18, 'backadmin', 'customerrole');


--
-- TOC entry 3167 (class 0 OID 16387)
-- Dependencies: 201
-- Data for Name: django_migrations; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.django_migrations VALUES (1, 'contenttypes', '0001_initial', '2021-01-06 18:44:20.139796+00');
INSERT INTO public.django_migrations VALUES (2, 'contenttypes', '0002_remove_content_type_name', '2021-01-06 18:44:20.155459+00');
INSERT INTO public.django_migrations VALUES (3, 'auth', '0001_initial', '2021-01-06 18:44:20.193259+00');
INSERT INTO public.django_migrations VALUES (4, 'auth', '0002_alter_permission_name_max_length', '2021-01-06 18:44:20.214978+00');
INSERT INTO public.django_migrations VALUES (5, 'auth', '0003_alter_user_email_max_length', '2021-01-06 18:44:20.224886+00');
INSERT INTO public.django_migrations VALUES (6, 'auth', '0004_alter_user_username_opts', '2021-01-06 18:44:20.234861+00');
INSERT INTO public.django_migrations VALUES (7, 'auth', '0005_alter_user_last_login_null', '2021-01-06 18:44:20.243447+00');
INSERT INTO public.django_migrations VALUES (8, 'auth', '0006_require_contenttypes_0002', '2021-01-06 18:44:20.246467+00');
INSERT INTO public.django_migrations VALUES (9, 'auth', '0007_alter_validators_add_error_messages', '2021-01-06 18:44:20.255045+00');
INSERT INTO public.django_migrations VALUES (10, 'auth', '0008_alter_user_username_max_length', '2021-01-06 18:44:20.264638+00');
INSERT INTO public.django_migrations VALUES (11, 'auth', '0009_alter_user_last_name_max_length', '2021-01-06 18:44:20.274538+00');
INSERT INTO public.django_migrations VALUES (12, 'auth', '0010_alter_group_name_max_length', '2021-01-06 18:44:20.284815+00');
INSERT INTO public.django_migrations VALUES (13, 'auth', '0011_update_proxy_permissions', '2021-01-06 18:44:20.303536+00');
INSERT INTO public.django_migrations VALUES (14, 'auth', '0012_alter_user_first_name_max_length', '2021-01-06 18:44:20.313554+00');
INSERT INTO public.django_migrations VALUES (15, 'backadmin', '0001_initial', '2021-01-06 18:44:20.370479+00');
INSERT INTO public.django_migrations VALUES (16, 'admin', '0001_initial', '2021-01-06 18:44:20.41397+00');
INSERT INTO public.django_migrations VALUES (17, 'admin', '0002_logentry_remove_auto_add', '2021-01-06 18:44:20.436259+00');
INSERT INTO public.django_migrations VALUES (18, 'admin', '0003_logentry_add_action_flag_choices', '2021-01-06 18:44:20.446939+00');
INSERT INTO public.django_migrations VALUES (19, 'sessions', '0001_initial', '2021-01-06 18:44:20.456857+00');


--
-- TOC entry 3184 (class 0 OID 16542)
-- Dependencies: 218
-- Data for Name: django_session; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3192 (class 0 OID 16589)
-- Dependencies: 226
-- Data for Name: payment_method; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3194 (class 0 OID 16596)
-- Dependencies: 228
-- Data for Name: phone; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3195 (class 0 OID 16605)
-- Dependencies: 229
-- Data for Name: price_range; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.price_range VALUES ('CHEAP', '2021-01-06 18:44:27.004', '2021-01-06 18:44:27.004');
INSERT INTO public.price_range VALUES ('NORMAL', '2021-01-06 18:44:27.014', '2021-01-06 18:44:27.014');
INSERT INTO public.price_range VALUES ('EXPENSIVE', '2021-01-06 18:44:27.022', '2021-01-06 18:44:27.022');


--
-- TOC entry 3197 (class 0 OID 16612)
-- Dependencies: 231
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product VALUES (6, '2021-01-06 18:52:33.015', '2021-01-06 18:52:33.015', true, 'Tomate, mozzarella, jamón york y champiñones', 'GARDA', NULL, 6.00, 12.00, 1);
INSERT INTO public.product VALUES (8, '2021-01-06 18:56:06.923', '2021-01-06 18:56:06.923', true, 'Pizza de la casa', 'Pizza', NULL, 7.00, 12.00, 2);
INSERT INTO public.product VALUES (2, '2021-01-06 18:52:33', '2021-01-06 18:52:33.018', true, 'Relleno de helado de vainilla biscotto', 'CREPE', NULL, 2.00, 5.00, 1);
INSERT INTO public.product VALUES (5, '2021-01-06 18:52:33', '2021-01-06 18:52:33.018', true, 'Crema de trufa blanca, mozzarella, foie mi-cuit, queso tomino, cebolla pochada y perlas de frambuesa.', 'BIANCA DI FOIE', NULL, 6.00, 12.00, 1);
INSERT INTO public.product VALUES (4, '2021-01-06 18:52:33', '2021-01-06 18:52:33.018', true, 'Tomate, mozzarella, grana padano DOP, gorgonzola DOP y emmental.', 'PIZZA 4 FORMAGGI', NULL, 4.00, 13.00, 1);
INSERT INTO public.product VALUES (1, '2021-01-06 18:52:33', '2021-01-06 18:52:33.018', true, 'Tarta de queso fresco y tocinillo de cielo.', 'BOCCONCINO', NULL, 3.00, 6.00, 1);
INSERT INTO public.product VALUES (3, '2021-01-06 18:52:33', '2021-01-06 18:52:33.019', true, 'Sapori: Vainilla biscotto, Nocciola (avellana), Leche merengada, Chocolate bombón, Fior di latte, Fresa y frutos rojos, Coco', 'COPPA AL GUSTO', NULL, 2.00, 3.00, 1);
INSERT INTO public.product VALUES (7, '2021-01-06 18:52:33', '2021-01-06 18:56:06.925', true, 'Hamburguesa clasica de la casa', 'Hamburguesa', NULL, 7.00, 12.00, 2);
INSERT INTO public.product VALUES (11, '2021-01-06 19:20:45.466', '2021-01-06 19:20:45.466', true, 'Chuletón de Buey Gallego de 1kg aprox', 'Chuletón de Buey', NULL, 20.00, 35.00, 4);
INSERT INTO public.product VALUES (10, NULL, '2021-01-06 19:20:45.468', true, 'Refresco de cola acompañado de una rodajita de limón', 'Refresco de Cola', NULL, 1.00, 2.00, 4);
INSERT INTO public.product VALUES (9, NULL, '2021-01-06 19:20:45.468', true, 'Clásico café solo con hielo', 'Café', NULL, 1.00, 2.00, 4);


--
-- TOC entry 3198 (class 0 OID 16621)
-- Dependencies: 232
-- Data for Name: product_tags; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3200 (class 0 OID 16626)
-- Dependencies: 234
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.role VALUES (1, '2021-01-06 18:44:26.812', '2021-01-06 18:44:26.812', 'ROLE_ADMIN');
INSERT INTO public.role VALUES (2, '2021-01-06 18:44:26.862', '2021-01-06 18:44:26.862', 'ROLE_USER');
INSERT INTO public.role VALUES (3, '2021-01-06 18:44:26.866', '2021-01-06 18:44:26.866', 'ROLE_SELLER');


--
-- TOC entry 3201 (class 0 OID 16632)
-- Dependencies: 235
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tag VALUES ('VEGAN', '2021-01-06 18:44:26.892', '2021-01-06 18:44:26.892');
INSERT INTO public.tag VALUES ('FISH', '2021-01-06 18:44:26.903', '2021-01-06 18:44:26.903');
INSERT INTO public.tag VALUES ('MEAT', '2021-01-06 18:44:26.908', '2021-01-06 18:44:26.908');
INSERT INTO public.tag VALUES ('GLUTEN_FREE', '2021-01-06 18:44:26.916', '2021-01-06 18:44:26.916');
INSERT INTO public.tag VALUES ('SUGAR_FREE', '2021-01-06 18:44:26.92', '2021-01-06 18:44:26.92');
INSERT INTO public.tag VALUES ('LIGTH', '2021-01-06 18:44:26.928', '2021-01-06 18:44:26.928');
INSERT INTO public.tag VALUES ('LIGTH_SPICE', '2021-01-06 18:44:26.933', '2021-01-06 18:44:26.933');
INSERT INTO public.tag VALUES ('SPICE', '2021-01-06 18:44:26.938', '2021-01-06 18:44:26.938');


--
-- TOC entry 3222 (class 0 OID 0)
-- Dependencies: 219
-- Name: address_address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.address_address_id_seq', 1, false);


--
-- TOC entry 3223 (class 0 OID 0)
-- Dependencies: 206
-- Name: auth_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.auth_group_id_seq', 1, true);


--
-- TOC entry 3224 (class 0 OID 0)
-- Dependencies: 208
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.auth_group_permissions_id_seq', 18, true);


--
-- TOC entry 3225 (class 0 OID 0)
-- Dependencies: 204
-- Name: auth_permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.auth_permission_id_seq', 72, true);


--
-- TOC entry 3226 (class 0 OID 0)
-- Dependencies: 222
-- Name: business_business_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.business_business_id_seq', 4, true);


--
-- TOC entry 3227 (class 0 OID 0)
-- Dependencies: 210
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.customer_customer_id_seq', 2, true);


--
-- TOC entry 3228 (class 0 OID 0)
-- Dependencies: 212
-- Name: customer_groups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.customer_groups_id_seq', 1, true);


--
-- TOC entry 3229 (class 0 OID 0)
-- Dependencies: 214
-- Name: customer_user_permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.customer_user_permissions_id_seq', 1, false);


--
-- TOC entry 3230 (class 0 OID 0)
-- Dependencies: 216
-- Name: django_admin_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.django_admin_log_id_seq', 4, true);


--
-- TOC entry 3231 (class 0 OID 0)
-- Dependencies: 202
-- Name: django_content_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.django_content_type_id_seq', 18, true);


--
-- TOC entry 3232 (class 0 OID 0)
-- Dependencies: 200
-- Name: django_migrations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.django_migrations_id_seq', 19, true);


--
-- TOC entry 3233 (class 0 OID 0)
-- Dependencies: 227
-- Name: phone_phone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.phone_phone_id_seq', 1, false);


--
-- TOC entry 3234 (class 0 OID 0)
-- Dependencies: 230
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_product_id_seq', 11, true);


--
-- TOC entry 3235 (class 0 OID 0)
-- Dependencies: 233
-- Name: role_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.role_role_id_seq', 3, true);


--
-- TOC entry 2990 (class 2606 OID 16562)
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (address_id);


--
-- TOC entry 2992 (class 2606 OID 16567)
-- Name: aggregate_rating aggregate_rating_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.aggregate_rating
    ADD CONSTRAINT aggregate_rating_pkey PRIMARY KEY (aggregate_rating_id);


--
-- TOC entry 2949 (class 2606 OID 16456)
-- Name: auth_group auth_group_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_group
    ADD CONSTRAINT auth_group_name_key UNIQUE (name);


--
-- TOC entry 2954 (class 2606 OID 16442)
-- Name: auth_group_permissions auth_group_permissions_group_id_permission_id_0cd325b0_uniq; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_group_permissions
    ADD CONSTRAINT auth_group_permissions_group_id_permission_id_0cd325b0_uniq UNIQUE (group_id, permission_id);


--
-- TOC entry 2957 (class 2606 OID 16431)
-- Name: auth_group_permissions auth_group_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_group_permissions
    ADD CONSTRAINT auth_group_permissions_pkey PRIMARY KEY (id);


--
-- TOC entry 2951 (class 2606 OID 16421)
-- Name: auth_group auth_group_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_group
    ADD CONSTRAINT auth_group_pkey PRIMARY KEY (id);


--
-- TOC entry 2944 (class 2606 OID 16433)
-- Name: auth_permission auth_permission_content_type_id_codename_01ab375a_uniq; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_permission
    ADD CONSTRAINT auth_permission_content_type_id_codename_01ab375a_uniq UNIQUE (content_type_id, codename);


--
-- TOC entry 2946 (class 2606 OID 16413)
-- Name: auth_permission auth_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_permission
    ADD CONSTRAINT auth_permission_pkey PRIMARY KEY (id);


--
-- TOC entry 2998 (class 2606 OID 16583)
-- Name: business_payment_method business_payment_method_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.business_payment_method
    ADD CONSTRAINT business_payment_method_pkey PRIMARY KEY (business_id, payment_method_id);


--
-- TOC entry 2994 (class 2606 OID 16578)
-- Name: business business_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.business
    ADD CONSTRAINT business_pkey PRIMARY KEY (business_id);


--
-- TOC entry 2960 (class 2606 OID 16470)
-- Name: customer customer_email_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_email_key UNIQUE (email);


--
-- TOC entry 2962 (class 2606 OID 16488)
-- Name: customer customer_email_password_c7949d33_uniq; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_email_password_c7949d33_uniq UNIQUE (email, password);


--
-- TOC entry 2971 (class 2606 OID 16491)
-- Name: customer_groups customer_groups_customer_id_group_id_95d0ab85_uniq; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_groups
    ADD CONSTRAINT customer_groups_customer_id_group_id_95d0ab85_uniq UNIQUE (customer_id, group_id);


--
-- TOC entry 2974 (class 2606 OID 16478)
-- Name: customer_groups customer_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_groups
    ADD CONSTRAINT customer_groups_pkey PRIMARY KEY (id);


--
-- TOC entry 2964 (class 2606 OID 16468)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- TOC entry 3000 (class 2606 OID 16588)
-- Name: customer_role customer_role_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_role
    ADD CONSTRAINT customer_role_pkey PRIMARY KEY (customer_id, role_id);


--
-- TOC entry 2976 (class 2606 OID 16505)
-- Name: customer_user_permissions customer_user_permission_customer_id_permission_i_be5423b4_uniq; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_user_permissions
    ADD CONSTRAINT customer_user_permission_customer_id_permission_i_be5423b4_uniq UNIQUE (customer_id, permission_id);


--
-- TOC entry 2980 (class 2606 OID 16486)
-- Name: customer_user_permissions customer_user_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_user_permissions
    ADD CONSTRAINT customer_user_permissions_pkey PRIMARY KEY (id);


--
-- TOC entry 2983 (class 2606 OID 16529)
-- Name: django_admin_log django_admin_log_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_admin_log
    ADD CONSTRAINT django_admin_log_pkey PRIMARY KEY (id);


--
-- TOC entry 2939 (class 2606 OID 16405)
-- Name: django_content_type django_content_type_app_label_model_76bd3d3b_uniq; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_content_type
    ADD CONSTRAINT django_content_type_app_label_model_76bd3d3b_uniq UNIQUE (app_label, model);


--
-- TOC entry 2941 (class 2606 OID 16403)
-- Name: django_content_type django_content_type_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_content_type
    ADD CONSTRAINT django_content_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2937 (class 2606 OID 16395)
-- Name: django_migrations django_migrations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_migrations
    ADD CONSTRAINT django_migrations_pkey PRIMARY KEY (id);


--
-- TOC entry 2987 (class 2606 OID 16549)
-- Name: django_session django_session_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_session
    ADD CONSTRAINT django_session_pkey PRIMARY KEY (session_key);


--
-- TOC entry 2966 (class 2606 OID 16642)
-- Name: customer email_indx; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT email_indx UNIQUE (email);


--
-- TOC entry 2968 (class 2606 OID 16640)
-- Name: customer email_password_active_indx; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT email_password_active_indx UNIQUE (email, password, is_active);


--
-- TOC entry 3002 (class 2606 OID 16593)
-- Name: payment_method payment_method_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT payment_method_pkey PRIMARY KEY (payment_method_id);


--
-- TOC entry 3004 (class 2606 OID 16604)
-- Name: phone phone_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.phone
    ADD CONSTRAINT phone_pkey PRIMARY KEY (phone_id);


--
-- TOC entry 3006 (class 2606 OID 16609)
-- Name: price_range price_range_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.price_range
    ADD CONSTRAINT price_range_pkey PRIMARY KEY (price_range_id);


--
-- TOC entry 3008 (class 2606 OID 16620)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- TOC entry 3010 (class 2606 OID 16631)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- TOC entry 3014 (class 2606 OID 16636)
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (tag_id);


--
-- TOC entry 3012 (class 2606 OID 16644)
-- Name: role uk_8sewwnpamngi6b1dwaa88askk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT uk_8sewwnpamngi6b1dwaa88askk UNIQUE (name);


--
-- TOC entry 2996 (class 2606 OID 16638)
-- Name: business uk_9xc0d95h3x1rc6q50xhao32jm; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.business
    ADD CONSTRAINT uk_9xc0d95h3x1rc6q50xhao32jm UNIQUE (fiscal_id);


--
-- TOC entry 2947 (class 1259 OID 16457)
-- Name: auth_group_name_a6ea08ec_like; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX auth_group_name_a6ea08ec_like ON public.auth_group USING btree (name varchar_pattern_ops);


--
-- TOC entry 2952 (class 1259 OID 16453)
-- Name: auth_group_permissions_group_id_b120cbf9; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX auth_group_permissions_group_id_b120cbf9 ON public.auth_group_permissions USING btree (group_id);


--
-- TOC entry 2955 (class 1259 OID 16454)
-- Name: auth_group_permissions_permission_id_84c5c92e; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX auth_group_permissions_permission_id_84c5c92e ON public.auth_group_permissions USING btree (permission_id);


--
-- TOC entry 2942 (class 1259 OID 16439)
-- Name: auth_permission_content_type_id_2f476e4b; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX auth_permission_content_type_id_2f476e4b ON public.auth_permission USING btree (content_type_id);


--
-- TOC entry 2958 (class 1259 OID 16489)
-- Name: customer_email_89e31bf1_like; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX customer_email_89e31bf1_like ON public.customer USING btree (email varchar_pattern_ops);


--
-- TOC entry 2969 (class 1259 OID 16502)
-- Name: customer_groups_customer_id_db50a81a; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX customer_groups_customer_id_db50a81a ON public.customer_groups USING btree (customer_id);


--
-- TOC entry 2972 (class 1259 OID 16503)
-- Name: customer_groups_group_id_902232a5; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX customer_groups_group_id_902232a5 ON public.customer_groups USING btree (group_id);


--
-- TOC entry 2977 (class 1259 OID 16516)
-- Name: customer_user_permissions_customer_id_26a7437e; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX customer_user_permissions_customer_id_26a7437e ON public.customer_user_permissions USING btree (customer_id);


--
-- TOC entry 2978 (class 1259 OID 16517)
-- Name: customer_user_permissions_permission_id_2c3e834e; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX customer_user_permissions_permission_id_2c3e834e ON public.customer_user_permissions USING btree (permission_id);


--
-- TOC entry 2981 (class 1259 OID 16540)
-- Name: django_admin_log_content_type_id_c4bce8eb; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX django_admin_log_content_type_id_c4bce8eb ON public.django_admin_log USING btree (content_type_id);


--
-- TOC entry 2984 (class 1259 OID 16541)
-- Name: django_admin_log_user_id_c564eba6; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX django_admin_log_user_id_c564eba6 ON public.django_admin_log USING btree (user_id);


--
-- TOC entry 2985 (class 1259 OID 16551)
-- Name: django_session_expire_date_a5c62663; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX django_session_expire_date_a5c62663 ON public.django_session USING btree (expire_date);


--
-- TOC entry 2988 (class 1259 OID 16550)
-- Name: django_session_session_key_c0390e0f_like; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX django_session_session_key_c0390e0f_like ON public.django_session USING btree (session_key varchar_pattern_ops);


--
-- TOC entry 3017 (class 2606 OID 16448)
-- Name: auth_group_permissions auth_group_permissio_permission_id_84c5c92e_fk_auth_perm; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_group_permissions
    ADD CONSTRAINT auth_group_permissio_permission_id_84c5c92e_fk_auth_perm FOREIGN KEY (permission_id) REFERENCES public.auth_permission(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3016 (class 2606 OID 16443)
-- Name: auth_group_permissions auth_group_permissions_group_id_b120cbf9_fk_auth_group_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_group_permissions
    ADD CONSTRAINT auth_group_permissions_group_id_b120cbf9_fk_auth_group_id FOREIGN KEY (group_id) REFERENCES public.auth_group(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3015 (class 2606 OID 16434)
-- Name: auth_permission auth_permission_content_type_id_2f476e4b_fk_django_co; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.auth_permission
    ADD CONSTRAINT auth_permission_content_type_id_2f476e4b_fk_django_co FOREIGN KEY (content_type_id) REFERENCES public.django_content_type(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3018 (class 2606 OID 16492)
-- Name: customer_groups customer_groups_customer_id_db50a81a_fk_customer_customer_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_groups
    ADD CONSTRAINT customer_groups_customer_id_db50a81a_fk_customer_customer_id FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3019 (class 2606 OID 16497)
-- Name: customer_groups customer_groups_group_id_902232a5_fk_auth_group_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_groups
    ADD CONSTRAINT customer_groups_group_id_902232a5_fk_auth_group_id FOREIGN KEY (group_id) REFERENCES public.auth_group(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3020 (class 2606 OID 16506)
-- Name: customer_user_permissions customer_user_permis_customer_id_26a7437e_fk_customer_; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_user_permissions
    ADD CONSTRAINT customer_user_permis_customer_id_26a7437e_fk_customer_ FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3021 (class 2606 OID 16511)
-- Name: customer_user_permissions customer_user_permis_permission_id_2c3e834e_fk_auth_perm; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_user_permissions
    ADD CONSTRAINT customer_user_permis_permission_id_2c3e834e_fk_auth_perm FOREIGN KEY (permission_id) REFERENCES public.auth_permission(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3022 (class 2606 OID 16530)
-- Name: django_admin_log django_admin_log_content_type_id_c4bce8eb_fk_django_co; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_admin_log
    ADD CONSTRAINT django_admin_log_content_type_id_c4bce8eb_fk_django_co FOREIGN KEY (content_type_id) REFERENCES public.django_content_type(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3023 (class 2606 OID 16535)
-- Name: django_admin_log django_admin_log_user_id_c564eba6_fk_customer_customer_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.django_admin_log
    ADD CONSTRAINT django_admin_log_user_id_c564eba6_fk_customer_customer_id FOREIGN KEY (user_id) REFERENCES public.customer(customer_id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3026 (class 2606 OID 16655)
-- Name: business fk1kdm1jwkfl9t7iv713h525etf; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.business
    ADD CONSTRAINT fk1kdm1jwkfl9t7iv713h525etf FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- TOC entry 3034 (class 2606 OID 16695)
-- Name: product_tags fk20byrdu0ih5vhs4k5wtq6p8xw; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_tags
    ADD CONSTRAINT fk20byrdu0ih5vhs4k5wtq6p8xw FOREIGN KEY (product_id) REFERENCES public.tag(tag_id);


--
-- TOC entry 3024 (class 2606 OID 16645)
-- Name: address fk93c3js0e22ll1xlu21nvrhqgg; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT fk93c3js0e22ll1xlu21nvrhqgg FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- TOC entry 3033 (class 2606 OID 16690)
-- Name: product fkbxgk89jvyti6iaqnuevrlwt6r; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkbxgk89jvyti6iaqnuevrlwt6r FOREIGN KEY (business_id) REFERENCES public.business(business_id);


--
-- TOC entry 3025 (class 2606 OID 16650)
-- Name: business fke26md4xgb70ly3m7pd9wbr5tj; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.business
    ADD CONSTRAINT fke26md4xgb70ly3m7pd9wbr5tj FOREIGN KEY (aggregate_rating_id) REFERENCES public.aggregate_rating(aggregate_rating_id);


--
-- TOC entry 3032 (class 2606 OID 16685)
-- Name: phone fkgvgc4f2c3qkiswyv6bbs0br1p; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.phone
    ADD CONSTRAINT fkgvgc4f2c3qkiswyv6bbs0br1p FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- TOC entry 3031 (class 2606 OID 16680)
-- Name: customer_role fkipr3etk2mjwkv6ahb4x4vwqy3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_role
    ADD CONSTRAINT fkipr3etk2mjwkv6ahb4x4vwqy3 FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- TOC entry 3029 (class 2606 OID 16670)
-- Name: business_payment_method fkjq3jw1sj9hil8jdfjs1ufbg73; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.business_payment_method
    ADD CONSTRAINT fkjq3jw1sj9hil8jdfjs1ufbg73 FOREIGN KEY (business_id) REFERENCES public.business(business_id);


--
-- TOC entry 3030 (class 2606 OID 16675)
-- Name: customer_role fkmwml8muyov9xhw4423lp88n2u; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_role
    ADD CONSTRAINT fkmwml8muyov9xhw4423lp88n2u FOREIGN KEY (role_id) REFERENCES public.role(role_id);


--
-- TOC entry 3027 (class 2606 OID 16660)
-- Name: business fkngeqwh7nmr3xkbu1abs1ra372; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.business
    ADD CONSTRAINT fkngeqwh7nmr3xkbu1abs1ra372 FOREIGN KEY (price_range_id) REFERENCES public.price_range(price_range_id);


--
-- TOC entry 3035 (class 2606 OID 16700)
-- Name: product_tags fkt47tap1r58f6jn0fpt9umq3w1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_tags
    ADD CONSTRAINT fkt47tap1r58f6jn0fpt9umq3w1 FOREIGN KEY (tag_id) REFERENCES public.product(product_id);


--
-- TOC entry 3028 (class 2606 OID 16665)
-- Name: business_payment_method fktl5rvnjq229gbntmamtoemlyc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.business_payment_method
    ADD CONSTRAINT fktl5rvnjq229gbntmamtoemlyc FOREIGN KEY (payment_method_id) REFERENCES public.payment_method(payment_method_id);


-- Completed on 2021-01-06 20:23:33 CET

--
-- PostgreSQL database dump complete
--

