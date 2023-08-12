def process_user_input(line, lineNumber):
    errorText = "Check line " + str(lineNumber) + " of the input file."
    startAndEnd = ()
    try:
        startAndEnd = tuple(map(int, line.split(' ')))
    except:
        print(errorText, "Expected integers separated by space but read:", line)
        exit(1)
    if len(startAndEnd) != 2:
        print(errorText, "Expected two values but read:", line)
        exit(1)
    if startAndEnd[0] > startAndEnd[1]:
        print(errorText, "First number (left end of interval) is greater than second:", line)
        exit(1)
    return startAndEnd

def read_input(file):
    intervals = []
    input = open(file, 'r')
    lines = input.readlines()
    lineNumber = 0
    for line in lines:
        lineNumber += 1
        intervals.append(process_user_input(line, lineNumber))
    input.close()
    return intervals

def output_cover(file, cover):
    output = open(file, 'w')
    output.writelines(cover)
    output.close()

# input: list of intervals sorted by left ends
# output: cover maximum number of points and for this choose a minimal set of intervals
# each interval is only considered once, this should be the fastest possible algorithm
def find_best_cover(intervals):
    cover = []
    x = intervals[0][0]  # first point to be covered
    xIsCovered = False
    best = None  # best interval covering x
    index = 0
    while index < len(intervals):  # process intervals from left to right sorted by their left ends
        I = intervals[index]
        if I[0] <= x:  # left end of I <= x
            index += 1
            if I[1] >= x:  # right end of I >= x, so I covers x
                if (not xIsCovered) or (I[1] > x):
                    if best is None:
                        best = I
                    elif I[1] > best[1]:  # I covers more uncovered points than best
                            best = I
        else:  # all candidates covering x were considered (since the left end of I > x)
            if best is None:  # no interval can cover x
                x = I[0]  # move to the closest point x that can be covered (this point is not covered yet)
                xIsCovered = False
            else:  # some interval covers x
                cover.append(str(best[0]) + " " + str(best[1]) + "\n")
                x = best[1]  # move x to the right end of best (which is the right-most covered point)
                xIsCovered = True
                best = None
    # while ends
    if best is not None:  # the last point x can be covered but no interval was added yet
        cover.append(str(best[0]) + " " + str(best[1]) + "\n")
    return cover

intervals = read_input('input.txt')
# sort intervals by first value, that is, by the left ends
intervals.sort(key=lambda tup: tup[0])
cover = find_best_cover(intervals)
output_cover('output.txt', cover)