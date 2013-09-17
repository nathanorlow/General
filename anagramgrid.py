def addspaces(word,num):
	returnstring=''
	for i in range(len(word)):
		returnstring+=word[i]+' '*num
	return returnstring.rstrip(' ')

#these are twitter anagrams
firstword="Twitter machine"
secondword="Interact with me"
filename='twitter'

firstword=' '+addspaces(firstword.lower(),2)
secondword=' '+addspaces(secondword.lower(),1)

firstlength=len(firstword)
secondlength=len(secondword)
firstindices=[-1 for i in range(firstlength)]
secondindices=[-1 for i in range(secondlength)]

for secondi in range(secondlength):
	for firsti in range(firstlength-1,-1,-1):
		if (firstword[firsti] == secondword[secondi]) and (firstindices[firsti]==-1) and (secondword[secondi].isalpha() or secondword[secondi]=="'"):
			firstindices[firsti]=secondi
			secondindices[secondi]=firsti
			break
anagramout='\n'
anagramout+=(' |'+firstword + '\n')
anagramout+=('-+'+('-'*firstlength)+'\n')
for firsti in range(secondlength):
	outputline=''
	for secondi in range(firstlength):
		if firsti==firstindices[secondi]:
			outputline+=secondword[firsti]
		elif firsti<firstindices[secondi]: # |
			if secondi<secondindices[firsti]:#+
				#outputline+='+'
				#if secondi>firsti:
				#	outputline+=firstword[secondi]
				#else:
				outputline+=secondword[firsti]
			else:
				outputline+=firstword[secondi]#'|'
		else:#no |
			if secondi<secondindices[firsti]:#-
				outputline+=secondword[firsti]#'-'
			else:
				outputline+=' '
	anagramout+=(secondword[firsti]+'|'+outputline+'\n')
outputfile=open('C:/pyscript/anagram output/'+filename+'.txt','w')
print anagramout
outputfile.write(anagramout)
outputfile.close()