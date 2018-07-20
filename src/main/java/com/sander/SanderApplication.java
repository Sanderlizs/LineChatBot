package com.sander;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.google.common.io.ByteStreams;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.VideoMessageContent;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.VideoMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
@LineMessageHandler
@RestController
public class SanderApplication extends SpringBootServletInitializer {

  /**
   * https://github.com/line/line-bot-sdk-java/blob/f72acd981c51e6c428675dad52efc03c0b72d03d/sample-spring-boot-kitchensink/src/main/java/com/example/bot/spring/KitchenSinkController.java
   */

  static Path downloadedContentDir;

  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SanderApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(SanderApplication.class, args);
  }


  @RequestMapping("/sander")
  public String getMessage() {
    return "sander test 12345";
  }

  @GetMapping("/hello")
  public String getMessage2() {
    return "sander test 54321";
  }

  //取得文字訊息or網址
  @EventMapping
  public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   sander event: " + event);
    final String originalMessageText = event.getMessage().getText();
    // return new TextMessage(originalMessageText);
    return new TextMessage("get text");
  }

  @EventMapping
  public Message handleDefaultMessageEvent(Event event) {
    System.out.println("event: " + event);
    String replymessage = "handleDefaultMessageEvent";
    return new TextMessage(replymessage);
  }

  //取得貼圖
  @EventMapping
  public Message handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
    String replymessage = "get sticker";
    return new TextMessage(replymessage);
  }

  
  @EventMapping
  public Message handleLocationMessageEvent(MessageEvent<LocationMessageContent> event) {
    String replymessage = "get location";
    return new TextMessage(replymessage);
  }

  //取得圖檔
  @EventMapping
  public Message handleImageMessageEvent(MessageEvent<ImageMessageContent> event)
      throws IOException {
    String replymessage = "get image";
    return new TextMessage(replymessage);
  }

  //取得錄音檔檔
  @EventMapping
  public Message handleAudioMessageEvent(MessageEvent<AudioMessageContent> event)
      throws IOException {
    String replymessage = "get Audio";
    return new TextMessage(replymessage);
  }

  @EventMapping
  public Message handleVideoMessageEvent(MessageEvent<VideoMessageContent> event)
      throws IOException {
    String replymessage = "get Video";
    return new TextMessage(replymessage);
  }

  //
  @EventMapping
  public Message handleUnfollowEvent(UnfollowEvent event) {
    String replymessage = "get Unfollow";
    return new TextMessage(replymessage);
  }

  //
  @EventMapping
  public Message handleFollowEvent(FollowEvent event) {
    String replymessage = "get Follow";
    return new TextMessage(replymessage);
  }

  //邀請進群組or多人聊天
  @EventMapping
  public Message handleJoinEvent(JoinEvent event) {
    String replymessage = "get Join";
    return new TextMessage(replymessage);
  }

  //
  @EventMapping
  public Message handlePostbackEvent(PostbackEvent event) {
    String replymessage = "get Postback";
    return new TextMessage(replymessage);
  }

  //
  @EventMapping
  public Message handleBeaconEvent(BeaconEvent event) {
    String replymessage = "get Beacon";
    return new TextMessage(replymessage);
  }

  //
  @EventMapping
  public Message handleOtherEvent(Event event) {
    String replymessage = "get Other";
    return new TextMessage(replymessage);
  }


}
