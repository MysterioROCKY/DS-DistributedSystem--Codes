def display_vector(e1, e2, p1, p2):
    print()
    print("The vector timestamps of events in P1:")
    for i in range(e1):
        print(p1[i], end=" ")
    
    print()
    print("The vector timestamps of events in P2:")
    for i in range(e2):
        print(p2[i], end=" ")

def is_concurrent(v1, v2):
    return not all(v1[i] <= v2[i] for i in range(len(v1))) and not all(v2[i] <= v1[i] for i in range(len(v2)))

def vector_clock_with_concurrency(e1, e2, m):
    p1 = [[0, 0] for _ in range(e1)]
    p2 = [[0, 0] for _ in range(e2)]

    for i in range(e1):
        p1[i][0] = i + 1
        p1[i][1] = 0

    for i in range(e2):
        p2[i][0] = 0
        p2[i][1] = i + 1

    for i in range(e1):
        for j in range(e2):
            if m[i][j] == 1:  # Message sent
                p2[j] = [max(p2[j][0], p1[i][0] + 1), max(p2[j][1], p1[i][1] + 1)]
                for k in range(j + 1, e2):
                    p2[k] = [p2[k - 1][0] + 1, p2[k - 1][1] + 1]

            elif m[i][j] == -1:  # Message received
                p1[i] = [max(p1[i][0], p2[j][0] + 1), max(p1[i][1], p2[j][1] + 1)]
                for k in range(i + 1, e1):
                    p1[k] = [p1[k - 1][0] + 1, p1[k - 1][1] + 1]

    display_vector(e1, e2, p1, p2)

    # Detect and report concurrent events
    for i in range(e1):
        for j in range(e2):
            if is_concurrent(p1[i], p2[j]):
                print(f"\nEvent e1{i+1} and e2{j+1} are concurrent")

# Driver Code
if __name__ == "__main__":
    e1 = 5
    e2 = 3
    m = [[0]*3 for _ in range(5)]
    
    m[0][0] = 0
    m[0][1] = 0
    m[0][2] = 0
    m[1][0] = 0
    m[1][1] = 0
    m[1][2] = 1
    m[2][0] = 0
    m[2][1] = 0
    m[2][2] = 0
    m[3][0] = 0
    m[3][1] = 0
    m[3][2] = 0
    m[4][0] = 0
    m[4][1] = -1
    m[4][2] = 0
    
    vector_clock_with_concurrency(e1, e2, m)
