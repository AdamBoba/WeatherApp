Aplikacja Pogodowa dla Androida napisana w języku Kotlin. Pozwala użytkownikowi sprawdzić aktualną pogodę dla wybranego miasta, korzystając z API OpenWeatherMap. Użytkownik wprowadza nazwę miasta, a następnie otrzymuje informacje o temperaturze oraz opisie pogody. Dodatkowo, tło aplikacji zmienia się w zależności od warunków atmosferycznych.

Do realizacji tego celu wykorzystano kilka kluczowych bibliotek:

OkHttp: Biblioteka do wykonywania żądań sieciowych. Umożliwia łatwe tworzenie, wysyłanie i obsługę zapytań HTTP w aplikacji Android.
org.json: Biblioteka do pracy z formatem JSON. Używana do parsowania danych JSON otrzymanych z API OpenWeatherMap w celu wyodrębnienia potrzebnych informacji o pogodzie.
androidx.appcompat: Biblioteka Androida do tworzenia aplikacji kompatybilnych wstecznie z różnymi wersjami systemu Android. Zapewnia zestaw narzędzi i komponentów interfejsu użytkownika, które są zgodne z najnowszymi wytycznymi projektowania.
androidx.core.content.ContextCompat: Biblioteka Androida do dostępu do zasobów aplikacji, takich jak rysunki, kolory czy ciągi znaków. Używana w celu uzyskania dostępu do plików zasobów, takich jak ikony pogody wykorzystywane do ustawienia tła aplikacji w zależności od warunków pogodowych.
Te biblioteki są kluczowe dla funkcjonowania aplikacji i umożliwiają wygodne i efektywne tworzenie interfejsu użytkownika oraz integrację z zewnętrznymi serwisami, takimi jak OpenWeatherMap.

![zdj1](https://github.com/AdamBoba/WeatherApp/assets/113714262/93d7e6b7-df70-4b72-8017-8f5f7272247d)
![image](https://github.com/AdamBoba/WeatherApp/assets/113714262/258fdb42-36a1-46ae-bf8d-3cb843aebef6)
![image](https://github.com/AdamBoba/WeatherApp/assets/113714262/b9738f12-03b2-4f56-8eef-6fc7ce5ff3d3)
