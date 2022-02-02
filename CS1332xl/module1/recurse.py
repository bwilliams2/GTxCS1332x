d = [1, 7, 3, 5, 2, 8, 10, 24, -1, -5, 4]

def find_increase(d):
    subs = 1
    val = d[0]
    for n in range(1, len(d)):
        if d[n] > val:
            subs += 1
    if len(d) > 1:
        subs += find_increase(d[1:])
    return subs

a = find_increase(d)
print(a)