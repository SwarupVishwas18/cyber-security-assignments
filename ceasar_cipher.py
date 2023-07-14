from colorama import Fore

def enc(txt:str, shifts:int)->str:
   
    cipher = ''
    for char in txt:
        if char == ' ':
            cipher += char
        else:
            cipher += chr(ord(char) + shifts)
    return cipher


def dec(txt:str, shifts:int)->str:
    

    decipher = ''
    for char in txt:
        if char == ' ':
            decipher += char
        else:
            decipher += chr(ord(char) - shifts)
    return decipher

def main():
    print(Fore.GREEN + "Welcome to Caesar Cipher")
    statement = input("Enter a statement: ")
    shifts = int(input("Enter the number of shifts: "))
    print(Fore.GREEN + "Encrypted statement: ", enc(statement, shifts))
    print(Fore.GREEN + "Decrypted statement: ", dec(enc(statement, shifts), shifts  ))
    

if __name__ == '__main__':
    main()