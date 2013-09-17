def addspaces(word,num):
	returnstring=''
	for i in range(len(word)):
		returnstring+=word[i]+' '*num
	return returnstring.rstrip(' ')

filename='brilliant'
firstword='You are brilliant'.lower()
secondword='Ill eat your brain'.lower()

firstlength=len(firstword)
secondlength=len(secondword)
if firstlength<secondlength:
	firstword+=' '*(secondlength-firstlength)
	firstlength=len(firstword)
elif firstlength>secondlength:
	secondword+=' '*(firstlength-secondlength)
	secondlength=len(secondword)

currentword=list(firstword)
firstindices=[-1 for i in range(firstlength)]
secondindices=[-1 for i in range(secondlength)]
firstdirection=[0 for i in range(firstlength)]

print ''

for secondi in range(secondlength):
	for firsti in range(firstlength):
		if (firstword[firsti] == secondword[secondi]) and (firstindices[firsti]==-1):
			firstindices[firsti]=secondi
			firstdirection[firsti]=secondi-firsti
			secondindices[secondi]=firsti
			break

alloutput=''

count=0
alloutput+=addspaces(firstword,2)+'\n'
#for firsti in range(firstlength): #for multiple switches
while (not all([firstdirection[i]==0 for i in range(firstlength)])) and count<400:
	count+=1
	skipnext=False
	outputline1=''
	outputline2=''
	outputline3=''
	#print firstdirection
	#print currentword
	for firsti in range(firstlength-1): #the last index doesn't provide any choices
		if skipnext:
			skipnext=False
			#print 'skipping'
			continue
		dirone=firstdirection[firsti]
		dirtwo=firstdirection[firsti+1]
		letone=currentword[firsti]
		lettwo=currentword[firsti+1]
		#if (dirone>0 and dirtwo<=0) or (dirone>=0 and dirtwo<0):
		if dirone>dirtwo:
			#print 'switching %s and %s'%(letone,lettwo)
			outputline1+=' '+letone+lettwo+'   '
			outputline2+=' '+lettwo+letone+'   '
			outputline3+=lettwo+'  '+letone+'  '
			currentword[firsti]=lettwo
			currentword[firsti+1]=letone
			firstdirection[firsti]=dirtwo+1
			firstdirection[firsti+1]=dirone-1
			skipnext=True
			continue
		else:
			outputline1+=letone+'  '
			outputline2+=letone+'  '
			outputline3+=letone+'  '
	if not skipnext:
		outputline1+=currentword[firsti+1]
		outputline2+=currentword[firsti+1]
		outputline3+=currentword[firsti+1]
	alloutput+=outputline1.rstrip()+'\n'
	alloutput+=outputline2.rstrip()+'\n'
	alloutput+=outputline3.rstrip()+'\n'

outputfile=open('C:/pyscript/anagram switch/'+filename+'.txt','w')
print alloutput
outputfile.write(alloutput.rstrip())
outputfile.close()	
