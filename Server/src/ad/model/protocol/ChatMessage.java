package ad.model.protocol;

import java.io.Serializable;


/**
 * 
 * 
 * @author Anton Kostet
 * 
 * Copyright [2012] [Anton Kostet]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

public class ChatMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8161333432655648384L;
	private User from;
	private Group to;
	private String message;
	
	
	public ChatMessage(User from, Group to, String message)
	{
		this.from = from;
		this.to = to;
		this.message = message;
	}
	
	
	//Get-methods
	public User getUser()
	{
		return from;
	}

	public Group getGroup()
	{
		return to;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	
}