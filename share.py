#!/usr/bin/python

import smtplib  
from email.mime.text import MIMEText  
mailto_list="123997654@qq.com"
mail_host="smtp.gmail.com" 
mail_user="yhhong1943"    
mail_pass="hyh159263."
mail_postfix="gmail.com"
  
def send_mail(to_list,sub,content):
    me="hello"+"<"+mail_user+"@"+mail_postfix+">" 
    msg = MIMEText(content,_subtype='html',_charset='gb2312')    
    msg['Subject'] = sub    
    msg['From'] = me  
    msg['To'] = to_list  
    try:  
        s = smtplib.SMTP(mail_host,587)  

 	s.ehlo()
	s.starttls()
        s.login(mail_user,mail_pass)  
        s.sendmail(me, to_list, msg.as_string())  
        s.close()  
        return True  
    except Exception, e:  
        print str(e)  
        return False  
if __name__ == '__main__':  
    if send_mail(mailto_list,"hello","<a href='http://blog.csdn.net/yinghuihong'>yinghuihong..</a>"):  
        print "success"  
    else:  
        print "fail"  