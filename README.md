<h1>Проект по программированию на Java с использованием Scene Builder. Админ панель больницы с интерфейсом</h1>
<p><h2>Работа выполнена с помощью графического инструмента Java javaFX, с использованием SceneBuilder и базой данных PostgreSQL (Pg Admin)</h2></p>
<p><h3>Код для создания таблиц</h3></p>

<p><h3>-- Создание таблицы Healer</h3></p>
<p>CREATE TABLE healer((</p>
<p>  id SERIAL PRIMARY KEY,</p>
<p>  имя_врача VARCHAR(50),</p>
<p>  специальность VARCHAR(50),</p>
<p>   кабинет INT</p>
<p>);</p>

<p><h3>-- Создание таблицы Pacient</h3></p>
<p>CREATE TABLE pacient(</p>
<p>  id SERIAL PRIMARY KEY,</p>
<p>  имя_пациента VARCHAR(50),</p>
<p>  заболевание VARCHAR(50),</p>
<p>  лечащий_врач VARCHAR(50)</p>
<p>);</p>
