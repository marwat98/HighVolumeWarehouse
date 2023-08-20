package org.Engineering;

import java.util.Collection;
import java.util.List;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

public class ChatGPTConnector {
	OpenAiService service;
	
	
	public ChatGPTConnector() {
		//object which has primary code generate by OpenAi
		service = new  OpenAiService("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");
	}
	
	// method which add question 
	public String addSugestionForPallete(List<String> dates,String addDates) {
		
		String allWeight = String.join(",", dates);
		String question = "Mam na regale palety z " + dates + "Zaproponuj mi usunięcie palet które daty są wcześniejsze od podanej " + addDates + "Proszę o dokładne prześledzenie dat i wypisanie całej zawartości" ;
		
		return askChatGPT(question);
	}
	
	//method which supports the question
	private String askChatGPT(String question) {
		ChatCompletionRequest completionRequest = ChatCompletionRequest.builder().messages(List.of(new ChatMessage("user", question)))
		.model("gpt-3.5-turbo").build();
		
		 List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();
		 
		 StringBuilder builder = new StringBuilder();
		 
		 choices.stream().map(ChatCompletionChoice :: getMessage)
		 .map(ChatMessage :: getContent)
		 .forEach(builder :: append);
		 
		 return builder.toString();
		 
		 
	}

}
