PGDMP     (                
    x            alumnos    13.0    13.0     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16394    alumnos    DATABASE     f   CREATE DATABASE alumnos WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE alumnos;
                postgres    false            �            1259    16397    registro    TABLE     j   CREATE TABLE public.registro (
    id integer NOT NULL,
    nombre character varying,
    edad integer
);
    DROP TABLE public.registro;
       public         heap    postgres    false            �            1259    16395    registro_id_seq    SEQUENCE     �   CREATE SEQUENCE public.registro_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.registro_id_seq;
       public          postgres    false    201            �           0    0    registro_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.registro_id_seq OWNED BY public.registro.id;
          public          postgres    false    200            #           2604    16400    registro id    DEFAULT     j   ALTER TABLE ONLY public.registro ALTER COLUMN id SET DEFAULT nextval('public.registro_id_seq'::regclass);
 :   ALTER TABLE public.registro ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    200    201    201            �          0    16397    registro 
   TABLE DATA           4   COPY public.registro (id, nombre, edad) FROM stdin;
    public          postgres    false    201   O
       �           0    0    registro_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.registro_id_seq', 2, true);
          public          postgres    false    200            %           2606    16405    registro registro_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.registro
    ADD CONSTRAINT registro_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.registro DROP CONSTRAINT registro_pkey;
       public            postgres    false    201            �   0   x�3����H��43�2�NMJ,.�L�Sp.M��I,�426����� �3
�     