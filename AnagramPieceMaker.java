package anagram;

public class AnagramPieceMaker {
	String firstPhrase;
	String secondPhrase;
	
	//not done as a constructor because it may change later through "get new text"
	public void setup(String newFirstPhrase, String newSecondPhrase){
		firstPhrase=newFirstPhrase.toLowerCase();
		secondPhrase=newSecondPhrase.toLowerCase();
	}

	public String makePiece(int pieceNum){
		int lengthOfPhrase=firstPhrase.length();
		int secondLength=secondPhrase.length();
		int[] displacement;
		boolean[] targetUnused;
		int[] coordx;
		int[] coordy;
		int movementx;
		int movementy;
		int pausestep;
		int i,j;
		char[] outline;
		String output="<html>";
		char spacechar='.';
		
		int offset=2;
		int maxFrames=lengthOfPhrase+secondLength-1+offset;//This can change if desired
		System.out.println(maxFrames);
		
		displacement=new int[lengthOfPhrase];
		targetUnused=new boolean[secondLength];
		coordx=new int[lengthOfPhrase];
		coordy=new int[lengthOfPhrase];
		outline=new char[lengthOfPhrase+secondLength+offset];
		for (i=0; i<secondLength; i++){
			targetUnused[i]=true;
		}
			
		for (i=0; i<lengthOfPhrase; i++){
			displacement[i]=0;
			for(j=0; j<secondLength; j++){
				if ((firstPhrase.charAt(i)==secondPhrase.charAt(j))&&targetUnused[j]){
					displacement[i]=j-i;
					targetUnused[j]=false;
					break;
				}
				else if (j==secondLength-1){
					firstPhrase=firstPhrase.substring(0,i)+' '+firstPhrase.substring(i+1);//replace character with space
					System.out.println(firstPhrase);
				}
			}
		}	

		for (i=0; i<lengthOfPhrase; i++){
			movementx=displacement[i]+offset+lengthOfPhrase;
			if ((maxFrames-movementx)%2==0){
				pausestep=0;}
			else{
				pausestep=1;}
			movementy=(maxFrames-movementx)/2+1;//integer division
			//System.out.println("Letter "+firstPhrase.charAt(i)+" will move ("+Integer.toString(movementx)+","+Integer.toString(movementy)+") and pause "+Integer.toString(pausestep));
			//movementy is how much the letter moves up and down
			//movementx is how much the letter moves right
			if (pieceNum<=movementy){
				coordy[i]=lengthOfPhrase-pieceNum;
				coordx[i]=i;
			}
			else if (pieceNum<=movementx+movementy){//should agree with previous case at pieceNum=movementy
				coordy[i]=lengthOfPhrase-movementy;
				coordx[i]=i+pieceNum-movementy;
			}
			else{
				coordy[i]=lengthOfPhrase-movementy+(pieceNum-movementx-movementy)-pausestep;//pause deals with parity issues
				coordx[i]=i+movementx;
			}
			if (coordy[i]>lengthOfPhrase){
				coordy[i]=lengthOfPhrase;
			}
		}

		for (i=0; i<=lengthOfPhrase; i++){
			for (j=0; j<lengthOfPhrase+secondLength+offset; j++){
				outline[j]=spacechar;
			}
			
			for (j=0; j<lengthOfPhrase; j++){
				if(coordy[j]==i){
					outline[coordx[j]]=firstPhrase.charAt(j);
				}
			}
			
			for (j=0; j<lengthOfPhrase+secondLength+offset; j++){
				output+=outline[j];
			}
			output+="<br>";
		}
		output+="</html>";
		return output;
	}
}