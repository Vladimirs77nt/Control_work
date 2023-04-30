""" Задание 1. Приложение заметки (Python)

    Информация о проекте
Необходимо написать проект, содержащий функционал работы с заметками. Программа должна уметь создавать заметку,
сохранять её, читать список заметок, редактировать заметку, удалять заметку.
    
    Задание
Реализовать консольное приложение заметки, с сохранением, чтением, добавлением, редактированием и удалением заметок.
Заметка должна содержать идентификатор, заголовок, тело заметки и дату/время создания или последнего изменения заметки.
Сохранение заметок необходимо сделать в формате json или csv формат (разделение полей рекомендуется делать через точку
с запятой). Реализацию пользовательского интерфейса студент может делать как ему удобнее, можно делать как параметры
запуска программы (команда, данные), можно делать как запрос команды с консоли и последующим вводом данных, как-то ещё,
на усмотрение студента.

Например: python notes.py add --title "новая заметка" –msg "тело новой заметки"

Или так:
python note.py
Введите команду: add
Введите заголовок заметки: новая заметка
Введите тело заметки: тело новой заметки
Заметка успешно сохранена
Введите команду:

При чтении списка заметок реализовать фильтрацию по дате.
""" 
import datetime  
from datetime import datetime

file_name = 'notes_data.csv'
data = []
id_last = 0
id_j = 0
notes = []

# интерфейс меню
def interface():
    while True:
        print()
        print("--- Заметки ---")
        print("Меню команд:")
        print("1 - показать список всех заметок (сортировка по ID)")
        print("2 - показать список всех заметок (сортировка по дате)")
        print("3 - показать список всех заметок (сортировка по заголовку)")
        print("4 - показать список заметок по выбранной дате")
        print("5 - создать новую заметку")
        print("6 - редактировать существующую заметку (по ID)")
        print("7 - удалить заметку (по ID)")
        print("8 - сохранить заметки")
        print("0 - завершение работы")
        n = input(" > выберите команду: ")
        print()
        if n in ["1","2","3","4","5","6","7","8","0"]: break
        print(" >>> неверный ввод данных! <<<")
        print()
    return n

# контроллер меню
def controller ():
    global data
    while True:
        n = interface()
        if n == "0": break
        if n == "1":                      # 1 - показать список заметок (соритировка по ID)
            print_data(data, 0)
        if n == "2":                      # 2 - показать список заметок (сортировка по дате)
            print_data(data, 3)
        if n == "3":                      # 3 - показать список заметок (сортировка по заголовкам)
            print_data(data, 1)
        if n == "4":                      # 4 - показать заметки с фильтром по дате
            filter_data(data)
        if n == "5":                      # 5 - создать заметку
            data.append(add_note())
        if n == "6":                      # 6 - редактировать заметку по ID
            data = edit_notes_id()
        if n == "7":                      # 7 - удалить заметку по ID
            data = delete_notes_id()
        if n == "8":                      # 8 - сохранить заметки
            save_notes(data)

# чтение файл со списком заметок и формирование списка заметок data
def load_notes():
    global data
    global id_last
    id_last = 0

    try:
        with open(file_name, encoding='utf-8') as file:
            file = open(file_name, 'r')
    except FileNotFoundError:
        print(f">>> Внимание!!! Запрашиваемый файл {file_name} не найден!!!")
        print("...будет создан пустой файл")
        file = open(file_name, 'w')
        file.close()
        return

    data_file = [i.rstrip() for i in file]
    file.close()
    print("* файл {0} прочитан...".format(file_name))
    data_Str = [i.split(";") for i in data_file]
    for i in data_Str:
        i[0] = int(i[0])
        if i[0]>id_last: id_last = i[0]
        data.append(i)
    print ("последний ID =", id_last)

# 1 - вывод в терминал списка заметок
def print_data(data, index):
    data = sorted(data, key = lambda x: x[index])
    print("-"*138)
    print("|  ID  |  Заголовок         |  Заметка                                                                        | дата созд.(изм.) заметки |")
    print("-"*138)
    for i in data: print("|{0:^6}| {1:<19}| {2:<80}| {3:<25}|".format(i[0], i[1], i[2], i[3]))
    print("-"*138)

# 2 - фильтрация заметок по дате
def filter_data(data):
    print(">>> Фильтрация заметок по дате: ")
    format = "%Y-%m-%d"
    res = False
    while not res:
        date = input("Введите дату в формате: YYYY-MM-DD (год-месяц-день):")  
        try:
            res = bool(datetime.strptime(date, format))
        except ValueError:
            res = False
        if not res: print("Неверный ввод! пример ввода даты 2023-04-30 (30 апреля 2023 г.)...\n")
    date_check = datetime.strptime(date, '%Y-%m-%d').date()
    data_filter = []
    res = False
    for i in data:
        date_i = datetime.strptime(i[3], '%Y-%m-%d %H:%M').date()
        if date_check == date_i:
            data_filter.append(i)
            res = True
    if res: print_data(data_filter, 0)
    else: print ("На выбранную дату - заметок нет!")

# 3 - создание новой заметки
def add_note():
    global id_last
    print(">>> Добавление новой заметки: ")
    heading = input("Введите заголовок: ")
    notes = input("Введите заметку: ")
    id_last += 1
    current_date = datetime.now()
    current_date_string = current_date.strftime('%Y-%m-%d %H:%M')
    data_line = [int(id_last), heading, notes, current_date_string]
    print("новая запись: ", data_line)
    return data_line

# 4 - редактирование существующей заметки по ID
def edit_notes_id():
    global data
    print(" > Редактирование заметки")
    id = input_check_ID(data)
    if id == 0: return data

    global notes
    print(">>> Редактирование заметки с ID:", id)
    print("Текущий заголовок:", notes[1])
    text = input("Введите новый заголовок (Enter - если не требуется менять):")
    if len(text)>0:
        notes[1] = text
    print("Текущая заметка:", notes[2])
    text = input("Введите новую заметку (Enter - если не требуется менять):")
    if len(text)>0:
        notes[2] = text
    current_date = datetime.now()
    notes[3] = current_date.strftime('%Y-%m-%d %H:%M')
    print("Отредактированная заметка: ", notes)
    data[id_j] = notes
    return data

# 5 - удаление заметки по ID
def delete_notes_id():
    global data
    print(" > Удаление заметки")
    id = input_check_ID(data)
    if id == 0: return data
    
    data_new = []
    for i in data:
        if int(i[0]) != id: data_new.append(i)
    print(" >>> Заметка c ID {0} - удалена...".format(id))
    return data_new

# ввод и проверка ID заметки
def input_check_ID(data):
    while True:
        id = input("Введите ID номер записи (0 - отмена): ")
        if id.isnumeric():
            id = int(id)
            break
        print(" ошибка ввода! введите целое число!")
    global id_j, notes
    id_j = 0
    for i in data:
        if (i[0] == id):
            notes = i
            return id
        id_j += 1
    print(" >>> отмена или введенный ID не найден...")
    return 0
    
# 6 - сохранение списка заметок в файл
def save_notes(data):
    dstr = ""
    data = sorted(data, key = lambda x: x[0])
    for i in data:
        i[0] = str(i[0])
        for j in i:
            dstr = dstr + j + ";"
        i[0] = int(i[0])
        dstr = dstr[:-1] + '\n'
    dstr = dstr[:-1]
    f = open(file_name, 'w')
    f.write(dstr)
    f.close()
    print("* файл записан...")

# блок запуска программы
print()
print ("Запуск программы Notes (заметки)")
load_notes()
controller()