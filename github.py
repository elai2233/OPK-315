import random

while True:
    n = int(input('Введите кол-во телевизоров(1 - 2105): '))
    if n < 1 or n > 2105:
        print('Введено неправильное число!')
        continue
    break

print('')

i = int(0)
li = []
ri = []
MaxCountHours = []
MaxCountHours2 = []

while i < n:
    while True:
        li.append(random.randint(0, 109))
        ri.append(random.randint(0, 109))
        if li[i] >= ri[i]:
            li.pop()
            ri.pop()
        else:
            buf = li[i]
            while buf <= ri[i]:
                MaxCountHours.append(buf)
                buf += 1
            break
    print('Телевизор №', i + 1, 'работает с', li[i], 'по', ri[i])
    i += 1

print('')

foo = int(0)
i = 0
foo = bool(0)

print('Можно выключить телевизор(ы) №: ')

while i < n:
    buf = li[i]

    MaxCountHours2 = MaxCountHours.copy()

    while buf <= ri[i]:
        if buf in MaxCountHours2:
            MaxCountHours2.remove(buf)
        buf += 1

    buf = li[i]

    while buf <= ri[i]:
        if buf in MaxCountHours2:
            buf += 1
        else:
            break
    else:
        print(i + 1)
        foo = 1

    i += 1

if foo == 0:
    print(-1)
