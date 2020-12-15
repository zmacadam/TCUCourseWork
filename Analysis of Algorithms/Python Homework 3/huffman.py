import heapq

class heapnode:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None
    def __lt__(self, other):
        return self.freq < other.freq

codes = {}

def make_heap():
    print("Enter character:frequency(out of 100) separated by commas")
    print("Example: t:10, c:11, r:12, x:14, s:15, i:16, e:22")
    s2e = input()
    s2e = s2e.replace(':', ',')
    s2e = s2e.replace(' ', '')
    s2e = s2e.split(',')
    pq = []
    for i in range(0, len(s2e), 2):
        node = heapnode(s2e[i], int(s2e[i+1]))
        heapq.heappush(pq, node)
    return pq  

def create_tree(pq):
    n = len(pq)
    while len(pq) > 1:
        left = heapq.heappop(pq)
        right = heapq.heappop(pq)
        merge = heapnode(None, left.freq + right.freq)
        merge.left = left
        merge.right = right
        heapq.heappush(pq, merge)
    return(heapq.heappop(pq))

def make_codes(root):
    cur_code = ""
    make_codes_aux(root, cur_code)

def make_codes_aux(root, cur_code):
    if (root == None):
        return
    if (root.char != None):
        codes[root.char] = cur_code
        return
    make_codes_aux(root.left, cur_code + "0")
    make_codes_aux(root.right, cur_code + "1")

def accept_words():
    print("Enter words to encode only containing encoded letters ('quit' to terminate)")
    while 1:
        word = input()
        if (word == 'quit'):
            return
        else:
            encode_words(word)

def encode_words(word):
    encoded_word = ""
    for character in word:
        encoded_word += codes[character]
    print(encoded_word)

def __main__():
    heap = make_heap()
    root = create_tree(heap) 
    make_codes(root) 
    print(codes)
    accept_words()

__main__()