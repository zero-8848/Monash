"""
Testing file for Question 5 of Interview Prac 2

__author__ = "Saksham Nagpal"
__last_modified__ = 31.08.2021
"""

import unittest
from poke_team import PokeTeam
from battle import Battle


class TestTask5(unittest.TestCase):

    def setUp(self):
        self.verificationErrors = []

    def tearDown(self):
        for item in self.verificationErrors:
            print(item)
        print("Number of Errors = "+str(len(self.verificationErrors)))

    def test___conduct_combat(self):
        t1 = PokeTeam()
        t2 = PokeTeam()
        battle = Battle()
        battle_mode = 1
        t1.battle_mode = battle_mode
        t2.battle_mode = battle_mode

        # Test if combat is conducted correctly
        # Assumes __assign_team is working correctly
        t1._PokeTeam__assign_team("", 3, 2, 0)
        t2._PokeTeam__assign_team("", 1, 1, 1)
        try:
            self.assertTrue(battle._Battle__conduct_combat(t1, t2, battle_mode) == 1, "Set Mode 3,2,0 vs 1,1,1 failed")
        except AssertionError as e:
            self.verificationErrors.append(str(e))

        # your tests for __conduct_combat go here


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestTask5)
    unittest.TextTestRunner(verbosity=0).run(suite)

