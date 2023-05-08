package com.udemy.videolist.model;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Video {

  private Integer id;

  private String title;

  private String instructor;

  private String language;

  private Boolean isFree;

  private int price;

  public Video(String title, String instructor, String language, Boolean isFree, int price) {
    this.title = title;
    this.instructor = instructor;
    this.language = language;
    this.isFree = isFree;
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    // インスタンスの参照先(アドレス)が同じか(同一性を確認)
    if (this == o) {
      return true;
    }

    // nullもしくは異なるクラスのインスタンスではないか
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    // 引数として渡される際にObject型にアップキャストされフィールドやメソッドにアクセスできなくなっているので
    // ダウンキャストしてアクセスできるようにする
    Video video = (Video) o;

    // 全ての値(フィールド)が同じか比較した結果を返す
    return Objects.equals(id, video.id)
        && Objects.equals(title, video.title)
        && Objects.equals(instructor, video.instructor)
        && Objects.equals(language, video.language)
        && Objects.equals(price, video.price);
  }

  @Override
  public int hashCode() {
    // hashCodeメソッドはオブジェクトを一意に識別するためのメソッドであり、
    // 本来はオブジェクトのアドレスを基にハッシュコードを生成するように定義されている
    // しかしequalsメソッドで「同じ」の定義を変更するのであればそれに準じた仕様に変更する必要がある
    // こうすることでオブジェクトは「同じ」の定義に基づいたハッシュコード値を返すようになる
    return Objects.hash(id, title, instructor, language, price);
  }
}
