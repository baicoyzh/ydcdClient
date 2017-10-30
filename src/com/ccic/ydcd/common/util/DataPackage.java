package com.ccic.ydcd.common.util;
/**
 * 作者：陆峰毅
 * 说明：调用Tuxedo上的交易
 * 时间：2008-01-17
 */

public class DataPackage {
	private StringBuffer sendbuf;
    
    public DataPackage() {
    	this.sendbuf = new StringBuffer("");
    }
    
    public void addHeadItem ( String item, int len ) {
    	StringBuffer tmpItem = new StringBuffer("");
    	
    	//判断item不能为空，为空自动增加空格
    	if (item==null || item.length()<=0) {
    		for (int i=0;i<len;i++) { 
    			tmpItem.append(" ");
    		}
    	}
    	else {
    		tmpItem.append(item);
    	}
    	
    	//补足item的长度到len,以空格补
    	int tLen = tmpItem.toString().length();
    	if ( len > tLen ) {
    		for ( int i = 0; i<len-tLen; i++) {
    			tmpItem.append(" ");
    		}
    	}
    	
    	//处理item以及汉字问题
    	String tmp = tmpItem.toString();
    	
    	//int lens=0;
    	char cTmp [] = tmp.toCharArray();
    	for ( int i=0,k=0; i<len; i++ ) {
    		if (cTmp[i]>128) {
    			//汉字
    			k+=2;
    			if (k>=len) {
    				if (k == len) {
    					this.sendbuf.append(cTmp[i]);
    					break;
    				}
    				else {
    					this.sendbuf.append(" ");
    					break;
    				}
    			}
    			this.sendbuf.append(cTmp[i]);
    		}
    		else {
    			k++;
    			if (k>=len) {
    				this.sendbuf.append(cTmp[i]);
    				break;
    			}
    			else {
    				this.sendbuf.append(cTmp[i]);
    			}
    		}
    	}
    }
    
    public void addBodyItem ( String item, int len )
    {
        StringBuffer tmpItem = new StringBuffer("");
        
        //判断item不能为空，为空自动增加空格
        if (item==null || item.length()<=0)
        {
            for (int i=0;i<len;i++) tmpItem.append(" ");
        }
        else
        {
            tmpItem.append(item);
        }

        //补足item的长度到len,以空格补
        int tLen = tmpItem.toString().length();
        if ( len > tLen )
        {
           for ( int i = 0; i<len-tLen; i++)
                tmpItem.append(" ");    
        }
        
        
        //处理item以及汉字问题
        String tmp = tmpItem.toString();
        
        //int lens=0;
        char cTmp [] = tmp.toCharArray();
        for ( int i=0,k=0; i<len; i++ )
        {
            if (cTmp[i]>128)
            {   //汉字
                k+=2;
                if (k>=len)
                {
                    if (k == len)
                    {  
                        this.sendbuf.append(cTmp[i]);         
                        break;
                    }
                    else
                    {
                        this.sendbuf.append(" "); 
                        break;
                    }
                }
                this.sendbuf.append(cTmp[i]);            
            }
            else
            {
                k++;                
                if (k>=len)
                {
                    this.sendbuf.append(cTmp[i]);             
                    break;
                }
                else
                {
                    this.sendbuf.append(cTmp[i]); 
                }                             
            }
        }
    
    }
    
    public void clearbuf (){
        this.sendbuf.delete(0, sendbuf.length());
    }
    
    public String getSendbuf (){
        return this.sendbuf.toString();
    }
}
