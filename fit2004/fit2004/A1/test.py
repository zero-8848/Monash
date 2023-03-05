"""
Author: Satya Jhaveri
Unit: FIT2004
Purpose: Test Cases for FIT2004 Assignment 1, 2022 Semester 1
Disclaimer: This contains the test cases from the Assignment pdf, and some custom ones I added.
    I can't guarantee that the custom test cases are all right, but afaik they're correct, pls
    point out any errors tho
    
    Also just because your code has 0 errors doesn't mean you'll get 100% on your assignment, check 
    your code fits the time and space complexity requirements listed in the instructions, both 
    tasks have pretty (relatively) easy-to-code brute force solutions but that ain't what the markers
    are looking for.

To use this tester: Just put this file in the same directory as your assignment code file and run this 
    file(your code file must be named "assignment1.py", as per the assignment instructions).
"""
from assignment1 import trainer, local_maximum
import unittest

class TestLocalMaximum(unittest.TestCase):
    def setUp(self) -> None:
        # Create list of errors to collect over all tests:
        self.errorList = []
    
    def tearDown(self) -> None:
        # Print all errors found:
        for e in self.errorList:
            print(e)
        print(f"Number of errors: {len(self.errorList)}")
    
    def test_trainer(self) -> None:
        print("Testing trainer function")
        # Implementing the test cases from Assignment1.pdf:
        wordlist = ['limes', 'spare', 'store', 'loser', 'aster', 'pares', 'taser', 'pears', 'stare', 'spear', 'parse', 'reaps', 'rates', 'tears', 'losts']
        word1 = 'pares'
        marker1 = [0, 0, 0, 0, 1]
        output1 = ['reaps']
        
        word2 = 'pares'
        marker2 = [1, 0, 0, 0, 1]
        output2 = ['pears']
        
        word3 = 'pares'
        marker3 = [0, 0, 0, 0, 0]
        output3 = ['spare', 'spear']
        
        word4 = 'spare'
        marker4 = [1, 1, 0, 0, 1]
        output4 = []
        
        word5 = 'sprae'
        marker5 = [1, 1, 0, 0, 1]
        output5 = ['spare']
        
        words = [word1, word2, word3, word4, word5]
        markers = [marker1, marker2, marker3, marker4, marker5]
        outputs = [output1, output2, output3, output4, output5]
        
        for i in range(len(words)):
            word = words[i]
            marker = markers[i]
            output = outputs[i]
            
            try:
                res = trainer(wordlist, word, marker)
                self.assertTrue(res == output, msg=f"\nOutput for trainer(\n\twordlist={wordlist}, \n\tword={word}, \n\tmarker={marker}) \nincorrect. Expected result: {output}. Your function returned: {res}")
            except AssertionError as e:
                self.errorList.append(str(e))
            except Exception as e:
                self.errorList.append(f"trainer(\n\twordlist={wordlist}, \n\tword={word}, \n\tmarker={marker}) raised the error: {str(e)}")
        
        # Implementing my own silly little test cases to check my 
        #   silly little function does what it says on the tin 
        #   and isn't being too goofy:
        wordlist = ['sssuy', 'sssyu', 'ssusy', 'ssuys', 'ssysu', 'ssyus', 'sussy', 'susys', 'suyss', 'syssu', 
                    'sysus', 'syuss', 'usssy', 'ussys', 'usyss', 'uysss', 'ysssu', 'yssus', 'ysuss', 'yusss']
        word1 = "ligma"
        marker1 = [1, 0, 1, 0, 0]
        output1 = []
        
        word2 = "sussy"
        marker2 = [1, 1, 1, 0, 0]
        output2 = ['susys']
        
        word3 = "sussy"
        marker3 = [1, 0, 0, 0, 0]
        output3 = ['ssuys', 'ssyus']
        
        words = [word1, word2, word3]
        markers = [marker1, marker2, marker3]
        outputs = [output1, output2, output3]
        
        for i in range(len(words)):
            word = words[i]
            marker = markers[i]
            output = outputs[i]
            
            try:
                res = trainer(wordlist, word, marker)
                self.assertTrue(res == output, msg=f"\nOutput for trainer(\n\twordlist={wordlist}, \n\tword={word}, \n\tmarker={marker}) \nincorrect. Expected result: {output}. Your function returned: {res}")
            except AssertionError as e:
                self.errorList.append(str(e))
            except Exception as e:
                self.errorList.append(f"trainer(\n\twordlist={wordlist}, \n\tword={word}, \n\tmarker={marker}) raised the error: {str(e)}")
        
        
        wordlist = ['agmosu', 'agmous', 'agmsou', 'agmsuo', 'agmuos', 'agomsu', 'agomus', 'agosmu', 'agosum', 'agoums', 'agousm', 'agsmou', 'agsmuo', 'agsomu', 'agsoum', 
                    'agsumo', 'agsuom', 'agumos', 'agumso', 'aguoms', 'aguosm', 'agusmo', 'agusom', 'amgosu', 'amgous', 'amgsou', 'amgsuo', 'amguos', 'amogsu', 'amogus', 
                    'amosgu', 'amougs', 'amsgou', 'amsguo', 'amsogu', 'amsugo', 'amugos', 'amuogs', 'amusgo', 'aogmsu', 'aogmus', 'aogsmu', 'aogsum', 'aogums', 'aogusm', 
                    'aomgsu', 'aomgus', 'aomsgu', 'aomugs', 'aosgmu', 'aosgum', 'aosmgu', 'aosugm', 'aougms', 'aougsm', 'aoumgs', 'aousgm', 'asgmou', 'asgmuo', 'asgomu', 
                    'asgoum', 'asgumo', 'asguom', 'asmgou', 'asmguo', 'asmogu', 'asmugo', 'asogmu', 'asogum', 'asomgu', 'asougm', 'asugmo', 'asugom', 'asumgo', 'asuogm', 
                    'augmos', 'augmso', 'augoms', 'augosm', 'augsmo', 'augsom', 'aumgos', 'aumogs', 'aumsgo', 'auogms', 'auogsm', 'auomgs', 'auosgm', 'ausgmo', 'ausgom', 
                    'ausmgo', 'ausogm', 'gamosu', 'gamous', 'gamsou', 'gamsuo', 'gamuos', 'gaomsu', 'gaomus', 'gaosmu', 'gaosum', 'gaoums', 'gaousm', 'gasmou', 'gasmuo', 
                    'gasomu', 'gasoum', 'gasumo', 'gasuom', 'gaumos', 'gaumso', 'gauoms', 'gauosm', 'gausmo', 'gausom', 'gmaosu', 'gmaous', 'gmasou', 'gmasuo', 'gmauos', 
                    'gmoasu', 'gmoaus', 'gmosau', 'gmouas', 'gmsaou', 'gmsauo', 'gmsoau', 'gmsuao', 'gmuaos', 'gmuoas', 'gmusao', 'goamsu', 'goamus', 'goasmu', 'goasum', 
                    'goaums', 'goausm', 'gomasu', 'gomaus', 'gomsau', 'gomuas', 'gosamu', 'gosaum', 'gosmau', 'gosuam', 'gouams', 'gouasm', 'goumas', 'gousam', 'gsamou', 
                    'gsamuo', 'gsaomu', 'gsaoum', 'gsaumo', 'gsauom', 'gsmaou', 'gsmauo', 'gsmoau', 'gsmuao', 'gsoamu', 'gsoaum', 'gsomau', 'gsouam', 'gsuamo', 'gsuaom', 
                    'gsumao', 'gsuoam', 'guamos', 'guamso', 'guaoms', 'guaosm', 'guasmo', 'guasom', 'gumaos', 'gumoas', 'gumsao', 'guoams', 'guoasm', 'guomas', 'guosam', 
                    'gusamo', 'gusaom', 'gusmao', 'gusoam', 'magosu', 'magous', 'magsou', 'magsuo', 'maguos', 'maogsu', 'maogus', 'maosgu', 'maougs', 'masgou', 'masguo', 
                    'masogu', 'masugo', 'maugos', 'mauogs', 'mausgo', 'mgaosu', 'mgaous', 'mgasou', 'mgasuo', 'mgauos', 'mgoasu', 'mgoaus', 'mgosau', 'mgouas', 'mgsaou', 
                    'mgsauo', 'mgsoau', 'mgsuao', 'mguaos', 'mguoas', 'mgusao', 'moagsu', 'moagus', 'moasgu', 'moaugs', 'mogasu', 'mogaus', 'mogsau', 'moguas', 'mosagu', 
                    'mosgau', 'mouags', 'mougas', 'msagou', 'msaguo', 'msaogu', 'msaugo', 'msgaou', 'msgauo', 'msgoau', 'msguao', 'msoagu', 'msogau', 'msuago', 'msugao', 
                    'muagos', 'muaogs', 'muasgo', 'mugaos', 'mugoas', 'mugsao', 'muoags', 'muogas', 'musago', 'musgao', 'oagmsu', 'oagmus', 'oagsmu', 'oagsum', 'oagums', 
                    'oagusm', 'oamgsu', 'oamgus', 'oamsgu', 'oamugs', 'oasgmu', 'oasgum', 'oasmgu', 'oasugm', 'oaugms', 'oaugsm', 'oaumgs', 'oausgm', 'ogamsu', 'ogamus', 
                    'ogasmu', 'ogasum', 'ogaums', 'ogausm', 'ogmasu', 'ogmaus', 'ogmsau', 'ogmuas', 'ogsamu', 'ogsaum', 'ogsmau', 'ogsuam', 'oguams', 'oguasm', 'ogumas', 
                    'ogusam', 'omagsu', 'omagus', 'omasgu', 'omaugs', 'omgasu', 'omgaus', 'omgsau', 'omguas', 'omsagu', 'omsgau', 'omuags', 'omugas', 'osagmu', 'osagum', 
                    'osamgu', 'osaugm', 'osgamu', 'osgaum', 'osgmau', 'osguam', 'osmagu', 'osmgau', 'osuagm', 'osugam', 'ouagms', 'ouagsm', 'ouamgs', 'ouasgm', 'ougams', 
                    'ougasm', 'ougmas', 'ougsam', 'oumags', 'oumgas', 'ousagm', 'ousgam', 'sagmou', 'sagmuo', 'sagomu', 'sagoum', 'sagumo', 'saguom', 'samgou', 'samguo', 
                    'samogu', 'samugo', 'saogmu', 'saogum', 'saomgu', 'saougm', 'saugmo', 'saugom', 'saumgo', 'sauogm', 'sgamou', 'sgamuo', 'sgaomu', 'sgaoum', 'sgaumo', 
                    'sgauom', 'sgmaou', 'sgmauo', 'sgmoau', 'sgmuao', 'sgoamu', 'sgoaum', 'sgomau', 'sgouam', 'sguamo', 'sguaom', 'sgumao', 'sguoam', 'smagou', 'smaguo', 
                    'smaogu', 'smaugo', 'smgaou', 'smgauo', 'smgoau', 'smguao', 'smoagu', 'smogau', 'smuago', 'smugao', 'soagmu', 'soagum', 'soamgu', 'soaugm', 'sogamu', 
                    'sogaum', 'sogmau', 'soguam', 'somagu', 'somgau', 'souagm', 'sougam', 'suagmo', 'suagom', 'suamgo', 'suaogm', 'sugamo', 'sugaom', 'sugmao', 'sugoam', 
                    'sugoma', 'sumago', 'sumgao', 'suoagm', 'suogam', 'uagmos', 'uagmso', 'uagoms', 'uagosm', 'uagsmo', 'uagsom', 'uamgos', 'uamogs', 'uamsgo', 'uaogms', 
                    'uaogsm', 'uaomgs', 'uaosgm', 'uasgmo', 'uasgom', 'uasmgo', 'uasogm', 'ugamos', 'ugamso', 'ugaoms', 'ugaosm', 'ugasmo', 'ugasom', 'ugmaos', 'ugmoas', 
                    'ugmsao', 'ugoams', 'ugoasm', 'ugomas', 'ugosam', 'ugsamo', 'ugsaom', 'ugsmao', 'ugsoam', 'umagos', 'umaogs', 'umasgo', 'umgaos', 'umgoas', 'umgsao', 
                    'umoags', 'umogas', 'umsago', 'umsgao', 'uoagms', 'uoagsm', 'uoamgs', 'uoasgm', 'uogams', 'uogasm', 'uogmas', 'uogsam', 'uomags', 'uomgas', 'uosagm', 
                    'uosgam', 'usagmo', 'usagom', 'usamgo', 'usaogm', 'usgamo', 'usgaom', 'usgmao', 'usgoam', 'usmago', 'usmgao', 'usoagm', 'usogam']
        
        word1 = "agmosu"
        marker1 = [1, 0, 0, 0, 1, 1]
        output1 = ['amogsu', 'aogmsu']
        
        word2 = "ogsuma"
        marker2 = [0, 0, 0, 0, 1, 1]
        output2 = ['sugoma']
        
        word3 = "amogus"
        marker3 = [0, 0, 0, 0, 0, 0]
        output3 = ['gamosu', 'gamsou', 'gasmou', 'gasomu', 'gasumo', 'gasuom', 'gaumso', 'gauosm', 'gausmo', 'gausom', 'goamsu', 
                   'goasmu', 'goausm', 'gomasu', 'gomsau', 'gosamu', 'gosmau', 'gosuam', 'gouasm', 'gousam', 'gsamou', 'gsaomu', 
                   'gsaumo', 'gsauom', 'gsmaou', 'gsmoau', 'gsmuao', 'gsuamo', 'gsuaom', 'gsumao', 'gsuoam', 'guamso', 'guaosm', 
                   'guasmo', 'guasom', 'gumsao', 'gusamo', 'gusaom', 'gusmao', 'gusoam', 'magosu', 'magsou', 'masogu', 'masugo', 
                   'mausgo', 'mgaosu', 'mgasou', 'mgsaou', 'mgsoau', 'mgsuao', 'mgusao', 'moasgu', 'mogasu', 'mogsau', 'mosagu', 
                   'msaogu', 'msaugo', 'msgaou', 'msgoau', 'msguao', 'msuago', 'muasgo', 'mugsao', 'musago', 'oagmsu', 'oagsmu', 
                   'oagusm', 'oamsgu', 'oasmgu', 'oasugm', 'oausgm', 'ogamsu', 'ogasmu', 'ogausm', 'ogmasu', 'ogmsau', 'ogsamu', 
                   'ogsmau', 'ogsuam', 'oguasm', 'ogusam', 'osamgu', 'osaugm', 'osgamu', 'osgmau', 'osguam', 'osmagu', 'osuagm', 
                   'ouasgm', 'ougasm', 'ougsam', 'ousagm', 'sagmou', 'sagomu', 'sagumo', 'saguom', 'samogu', 'samugo', 'saumgo', 
                   'sauogm', 'sgamou', 'sgaomu', 'sgaumo', 'sgauom', 'sgmaou', 'sgmoau', 'sgmuao', 'sguamo', 'sguaom', 'sgumao', 
                   'sguoam', 'soamgu', 'soaugm', 'sogamu', 'sogmau', 'soguam', 'somagu', 'souagm', 'suamgo', 'suaogm', 'sugamo', 
                   'sugaom', 'sugmao', 'sugoam', 'sugoma', 'sumago', 'uagmso', 'uagosm', 'uagsmo', 'uagsom', 'uamsgo', 'uasmgo', 
                   'uasogm', 'ugamso', 'ugaosm', 'ugasmo', 'ugasom', 'ugmsao', 'ugsamo', 'ugsaom', 'ugsmao', 'ugsoam', 'uoasgm', 
                   'uogasm', 'uogsam', 'uosagm', 'usamgo', 'usaogm', 'usgamo', 'usgaom', 'usgmao', 'usgoam', 'usmago']
        
        words = [word1, word2, word3]
        markers = [marker1, marker2, marker3]
        outputs = [output1, output2, output3]
        
        for i in range(len(words)):
            word = words[i]
            marker = markers[i]
            output = outputs[i]
            
            try:
                res = trainer(wordlist, word, marker)
                self.assertTrue(res == output, msg=f"\nOutput for trainer(\n\twordlist=the {i+1}th amogus one, \n\tword={word}, \n\tmarker={marker}) \nincorrect. Expected result: {output}. Your function returned: {res}")
            except AssertionError as e:
                self.errorList.append(str(e))
            except Exception as e:
                self.errorList.append(f"trainer(\n\twordlist=the {i+1}th amogus one, \n\tword={word}, \n\tmarker={marker}) raised the error: {str(e)}")
        
        # Testing empty lists:
        wordlist = []
        word = ""
        marker = []
        output = []
        try:
            res = trainer(wordlist, word, marker)
            self.assertTrue(res == output, msg=f"\nOutput for trainer(\n\twordlist={wordlist}, \n\tword={word}, \n\tmarker={marker}) \nincorrect. Expected result: {output}. Your function returned: {res}")
        except AssertionError as e:
            self.errorList.append(str(e))
        except Exception as e:
            self.errorList.append(f"trainer(\n\twordlist={wordlist}, \n\tword={word}, \n\tmarker={marker}) raised the error: {str(e)}")
        
        
    def test_local_maximum(self) -> None:
        print("Testing local_maximum function")
        ## Implementing test cases from Assignment1.pdf:
        matrix_1 = [[1,  2,  27, 28, 29, 30, 49],
                    [3,  4,  25, 26, 31, 32, 48],
                    [5,  6,  23, 24, 33, 34, 47],
                    [7,  8,  21, 22, 35, 36, 46],
                    [9,  10, 19, 20, 37, 38, 45],
                    [11, 12, 17, 18, 39, 40, 44],
                    [13, 14, 15, 16, 41, 42, 43]]
        local_maximums_1 = [[0, 6]]
        
        matrix_2 = [[1,  3,  6,  10, 15, 21, 28],
                    [2,  5,  9,  14, 20, 27, 34],
                    [4,  8,  13, 19, 26, 33, 39],
                    [7,  12, 18, 25, 32, 38, 90],
                    [11, 17, 24, 31, 37, 57, 91],
                    [99, 98, 97, 60, 59, 58, 56],
                    [22, 29, 35, 40, 44, 55, 49]]
        local_maximums_2 = [[5, 0], [4, 6]]
        
        matrix_3 = [[1,  3,  6,  10, 15, 21, 28],
                    [2,  5,  9,  14, 20, 27, 34],
                    [4,  8,  13, 19, 50, 33, 39],
                    [7,  12, 18, 25, 32, 38, 51],
                    [52, 17, 24, 31, 37, 42, 46],
                    [16, 23, 30, 36, 41, 45, 48],
                    [22, 29, 35, 40, 44, 47, 49]]
        local_maximums_3 = [[4, 0], [2, 4], [3, 6], [6, 6]]
        
        matrix_4 = [[1,   3,   6,   10,  15,  21,  28,  164, 201, 203, 206, 210, 215, 221, 228],
                    [2,   5,   9,   14,  20,  27,  34,  163, 202, 205, 209, 214, 220, 227, 234],
                    [4,   8,   13,  19,  26,  33,  39,  162, 204, 208, 213, 219, 226, 233, 239],
                    [7,   12,  18,  25,  32,  38,  43,  161, 207, 212, 218, 225, 232, 238, 290],
                    [11,  17,  24,  31,  37,  42,  46,  160, 211, 217, 224, 231, 909, 908, 907],
                    [16,  23,  30,  36,  41,  45,  48,  159, 216, 223, 230, 260, 906, 904, 902],
                    [22,  29,  35,  40,  44,  47,  49,  158, 222, 229, 235, 340, 305, 903, 901],
                    [51,  52,  53,  54,  55,  56,  57,  157, 506, 505, 504, 503, 502, 501, 650],
                    [101, 102, 127, 128, 129, 130, 149, 156, 601, 302, 327, 328, 629, 630, 649],
                    [103, 104, 125, 126, 131, 132, 148, 155, 603, 604, 625, 626, 631, 632, 648],
                    [105, 106, 123, 124, 133, 134, 147, 154, 605, 606, 623, 624, 633, 634, 647],
                    [107, 108, 121, 122, 135, 136, 146, 153, 607, 608, 621, 622, 635, 636, 646],
                    [109, 110, 119, 120, 137, 138, 145, 152, 609, 610, 619, 620, 637, 638, 645],
                    [111, 112, 117, 118, 139, 140, 144, 151, 611, 612, 617, 618, 639, 640, 644],
                    [113, 114, 115, 116, 141, 142, 143, 150, 613, 614, 615, 616, 641, 642, 643]]
        local_maximums_4 = [[4, 12]]
        
        ## Adding custom test cases:
        # Empty input case:
        matrix_5 = [[]]
        local_maximums_5 = [[]]  # the assignment instructions aren't super clear on what to do in this case, but i guess as long as ur func doesn't raise errors it's chill
        
        # 1x1 input case:
        matrix_6 = [[0]]
        local_maximums_6 = [[0, 0]]
        
        # Rando cases I auto generated: 
        matrix_7 = [[ 38,  52, -84,  49,  64,  73,  44, -86, -73,  12], 
                    [-50,  8,   65,  72,  11, -25,  8,   74,  44, -1 ], 
                    [ 42,  94,  0,  -55,  32, -42, -73,  24,  11,  81], 
                    [ 82, -88, -20,  99,  23,  79,  89,  0,  -91, -19], 
                    [-59,  49, -77,  54, -48,  81, -41, -10, -62,  62], 
                    [  0, -41, -54,  99, -62,  39, -38, -55,  79, -71], 
                    [ 73, -62, -57, -12,  93,  15, -36,  93,  70, -95], 
                    [ 59, -15,  69, -18,  40, -53, -99, -54, -89,  86], 
                    [ -1,  81,  6,   93, -60, -29, -85, -6,   64,  16], 
                    [-45, -24,  87,  29, -24, -89, -75, -33,  95,  13]]
        local_maximums_7 = [[0, 1], [0, 5], [0, 9], [1, 3], [1, 7], 
                            [2, 1], [2, 4], [2, 9], [3, 0], [3, 3], 
                            [3, 6], [4, 1], [4, 5], [4, 9], [5, 3], 
                            [5, 8], [6, 0], [6, 4], [6, 7], [7, 2], 
                            [7, 9], [8, 1], [8, 3], [8, 5], [9, 2], 
                            [9, 8]]
        
        matrix_8 = [[77, 24, 89, 75, 96], 
                    [47, 77, 26, 26, 58], 
                    [82, 51, 26, 60, 51], 
                    [98, 61, 90, 70, 75], 
                    [12, 43, 96, 51, 59]]
        local_maximums_8 = [[0, 0], [0, 2], [0, 4], 
                            [1, 1], [3, 0], [3, 4], 
                            [4, 2]]
        
        matrix_9 = [[81, 26, 97, 89, 40, 19, 70, 93, 76, 93], 
                    [18, 69, 77, 30, 76, 70, 46, 25, 26, 26], 
                    [17, 63, 2,  58, 96, 45, 95, 69, 63, 62], 
                    [17, 68, 24, 80, 88, 45, 2,  80, 79, 0 ], 
                    [18, 9,  77, 64, 6,  81, 71, 25, 0,  33], 
                    [13, 22, 63, 94, 62, 73, 34, 44, 80, 78], 
                    [99, 6,  46, 57, 20, 18, 91, 28, 96, 70], 
                    [71, 70, 65, 26, 34, 58, 31, 40, 47, 54], 
                    [78, 73, 77, 62, 41, 97, 55, 71, 68, 27], 
                    [28, 71, 14, 50, 60, 43, 74, 20, 42, 69]]
        local_maximums_9 = [[0, 0], [0, 2], [0, 7], [0, 9], 
                            [2, 4], [2, 6], [3, 1], [3, 7], 
                            [4, 0], [4, 2], [4, 5], [5, 3], 
                            [6, 0], [6, 6], [6, 8], [8, 0], 
                            [8, 2], [8, 5], [8, 7], [9, 4], 
                            [9, 6], [9, 9]]
        
        matrix_10 = [[53, 35, 62, 77, 30, 68, 47, 16, 99, 32, 38, 88, 90, 50, 94, 11, 98, 79, 71, 20], 
                     [52, 99, 9,  65, 7,  4,  74, 45, 66, 66, 74, 1,  51, 21, 32, 61, 89, 36, 27, 24], 
                     [12, 77, 33, 72, 26, 43, 5,  57, 88, 8,  30, 70, 55, 50, 14, 83, 19, 94, 38, 20], 
                     [41, 45, 10, 49, 6,  88, 9,  47, 87, 19, 54, 73, 77, 45, 64, 93, 53, 9,  69, 27], 
                     [74, 10, 5,  69, 95, 27, 54, 41, 96, 65, 46, 72, 1,  99, 48, 93, 53, 27, 23, 79], 
                     [38, 71, 24, 46, 23, 29, 58, 31, 65, 9,  85, 72, 61, 18, 66, 82, 51, 98, 65, 47], 
                     [96, 71, 58, 2,  48, 49, 81, 13, 57, 68, 15, 14, 69, 58, 87, 86, 82, 25, 1,  73], 
                     [58, 33, 43, 28, 49, 5,  93, 24, 28, 35, 65, 56, 93, 96, 91, 61, 16, 15, 24, 56], 
                     [8,  56, 39, 81, 37, 14, 10, 87, 85, 91, 63, 37, 60, 3,  92, 44, 88, 25, 60, 84], 
                     [81, 7,  97, 84, 14, 72, 99, 43, 61, 22, 44, 84, 38, 84, 13, 13, 96, 85, 40, 17], 
                     [83, 52, 97, 99, 22, 9,  67, 17, 23, 97, 41, 89, 61, 47, 58, 73, 39, 29, 50, 1 ], 
                     [47, 1,  30, 54, 10, 69, 71, 73, 75, 79, 67, 16, 91, 14, 39, 66, 54, 79, 29, 85], 
                     [51, 65, 30, 34, 51, 90, 15, 6,  63, 22, 85, 86, 50, 92, 70, 49, 50, 84, 32, 51], 
                     [85, 74, 19, 97, 92, 32, 72, 34, 11, 93, 80, 25, 76, 92, 60, 86, 69, 87, 12, 21], 
                     [73, 94, 84, 25, 66, 89, 59, 9,  9,  41, 3,  44, 17, 20, 6,  2,  27, 62, 76, 15], 
                     [2,  52, 60, 98, 77, 23, 76, 92, 90, 9,  13, 8,  3,  60, 40, 50, 37, 4,  47, 73], 
                     [23, 46, 53, 96, 75, 71, 59, 75, 80, 91, 66, 3,  16, 57, 96, 9,  85, 61, 99, 57], 
                     [76, 17, 78, 62, 32, 63, 66, 38, 47, 12, 93, 64, 31, 9,  21, 83, 99, 45, 41, 11], 
                     [65, 0,  49, 17, 35, 60, 1,  47, 12, 30, 31, 75, 38, 65, 28, 65, 26, 28, 5,  44], 
                     [81, 41, 79, 38, 1,  63, 28, 53, 26, 57, 88, 18, 3,  42, 80, 40, 32, 15, 27, 15]]
        local_maximums_10 = [[0,  0 ], [0,  3 ], [0,  5 ], [0,  8 ], [0,  12], [0,  14], [0,  16], [1,  1 ], [1,  6 ],
                             [1,  10], [2,  3 ], [2,  8 ], [2,  17], [3,  5 ], [3,  12], [3,  15], [3,  18], [4,  0 ],
                             [4,  4 ], [4,  8 ], [4,  13], [4,  15], [4,  19], [5,  1 ], [5,  10], [5,  17], [6,  0 ],
                             [6,  9 ], [6,  19], [7,  4 ], [7,  6 ], [7,  10], [7,  13], [8,  1 ], [8,  7 ], [8,  9 ],
                             [8,  14], [8,  19], [9,  2 ], [9,  6 ], [9,  13], [9,  16], [10, 0 ], [10, 3 ], [10, 9 ],
                             [10, 11], [10, 15], [10, 18], [11, 12], [11, 19], [12, 5 ], [12, 11], [12, 13], [13, 0 ],
                             [13, 3 ], [13, 6 ], [13, 9 ], [13, 13], [13, 15], [13, 17], [14, 1 ], [14, 5 ], [14, 11],
                             [14, 18], [15, 3 ], [15, 7 ], [15, 13], [15, 15], [15, 19], [16, 9 ], [16, 14], [16, 18],
                             [17, 0 ], [17, 2 ], [17, 6 ], [17, 10], [17, 16], [18, 11], [18, 13], [18, 19], [19, 0 ],
                             [19, 2 ], [19, 5 ], [19, 7 ], [19, 10], [19, 14], [19, 18]]
        
        matrix_11 = [[1, 1], [1, 1]]  # The assignment specs say that M will have all distinct integers so technically this one ain't needed
        local_maximums_11 = [[0, 0], [0, 1], [1, 0], [1, 1]]
        matrices = [matrix_1, matrix_2, matrix_3, matrix_4, matrix_5, matrix_6, matrix_7, matrix_8, matrix_9, matrix_10, matrix_11]
        local_maximums = [local_maximums_1 ,local_maximums_2, local_maximums_3, local_maximums_4, local_maximums_5, 
                          local_maximums_6, local_maximums_7, local_maximums_8, local_maximums_9, local_maximums_10, local_maximums_11]
        
        for i in range(len(matrices)):
            mat = matrices[i]
            sols = local_maximums[i]
            
            try:
                res = local_maximum(mat)
                self.assertTrue(res in sols, msg=f"Output for local_maximum(matrix_{i+1}) incorrect. Accepted results: {sols}. Your function returned: {res}")
            except AssertionError as e:
                self.errorList.append(str(e))
            except Exception as e:
                self.errorList.append(f"local_maximum(matrix_{i+1}) raised the error: {str(e)}")
                
    

if __name__ == "__main__":
    suite = unittest.TestLoader().loadTestsFromTestCase(TestLocalMaximum)
    unittest.TextTestRunner(verbosity=0).run(suite)