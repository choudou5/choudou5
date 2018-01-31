package com.alibaba.dingtalk.openapi.message;

import java.util.List;

/** 
  { 
    "message_url": "http://dingtalk.com", 
    "head": {
        "bgcolor": "FFCC0000"
    }, 
    "body": {
        "title": "标题", 
        "form": [
            {
                "key": "姓名", 
                "value": "张三"
            }, 
            {
                "key": "年龄", 
                "value": "30"
            }
        ], 
        "rich": {
            "num": "15.6", 
            "unit": "元"
        }, 
        "content": "大段文本", 
        "image": "@lADOAAGXIszazQKA", 
        "file_count": "3", 
        "author": "李四"
    }
 */
public class OAMessage extends Message {

	private String message_url;
	private Head head;
	private Body body;

	public OAMessage() {
	}

	public OAMessage(String message_url, Head head, Body body) {
		this.message_url = message_url;
		this.head = head;
		this.body = body;
	}

	@Override
	public String type() {
		return "oa";
	}
	
	//content
	public static class Head {
		private String text;
		private String bgcolor;

		public Head(String text, String bgcolor) {
			this.text = text;
			this.bgcolor = bgcolor;
		}

		public String getText() {
			return text;
		}

		public String getBgcolor() {
			return bgcolor;
		}

	}
	
	public static class Body {
		private String title;
		private List<Form> form;
		private Rich rich;
		private String content;
		private String image;
		private String file_found;
		private String author;
		
		public static class Form {
			private String key;
			private String value;

			public String getKey() {
				return key;
			}

			public void setKey(String key) {
				this.key = key;
			}

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}
		}
		
		public static class Rich {
			private String num;
			private String unit;

			public String getNum() {
				return num;
			}

			public void setNum(String num) {
				this.num = num;
			}

			public String getUnit() {
				return unit;
			}

			public void setUnit(String unit) {
				this.unit = unit;
			}
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public List<Form> getForm() {
			return form;
		}

		public void setForm(List<Form> form) {
			this.form = form;
		}

		public Rich getRich() {
			return rich;
		}

		public void setRich(Rich rich) {
			this.rich = rich;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getFile_found() {
			return file_found;
		}

		public void setFile_found(String file_found) {
			this.file_found = file_found;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}
	}

}
