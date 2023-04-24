package me.mrenxo.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

import java.util.HashMap;

public class Formatter {


    private static final MiniMessage format = MiniMessage.builder()
            .tags(TagResolver.builder()
                    .resolver(StandardTags.color())
                    .resolvers(TagResolver.resolver("cc", Formatter::createCC))
                    .resolvers(StandardTags.decorations()).build())
            .build();
    private static final LegacyComponentSerializer legacyFormat = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().character('&').build();

    public static Component text(String text) {
        return format.deserialize("<i:false>" + text);
    }
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', legacyFormat.serialize(format.deserialize("<i:false>" + text)));
    }


    static Tag createCC(final ArgumentQueue args, final Context ctx) {
        final String color = args.popOr("The <cc> tag requires exactly one argument, the link to open").value();

        return Tag.styling(colors.get(color));
    }

    private static HashMap<String, TextColor> colors = new HashMap<>();

    static {
        colors.put("main", TextColor.fromHexString("#3495eb"));
        colors.put("lmain", TextColor.fromHexString("#7dc2ff"));
        colors.put("white", TextColor.fromHexString("#e3f2ff"));
        colors.put("gray", TextColor.fromHexString("#aeb3b8"));
    }
    private static char[] c = new char[]{'k', 'm', 'b', 't'};

    public static String numberFormat(double n) {
        double d = ((long) n / 100) / 10.0;
        boolean isRound = (d * 10) %10 == 0;//true if the decimal part is equal to 0 (then it's trimmed anyway)
        return (d < 1000? //this determines the class, i.e. 'k', 'm' etc
                ((d > 99.9 || isRound || (!isRound && d > 9.99)? //this decides whether to trim the decimals
                        (int) d * 10 / 10 : d + "" // (int) d * 10 / 10 drops the decimal
                ) + "" + c[0])
                : numberFormat(d, 1));

    }
    public static String numberFormat(double n, int iteration) {
        double d = ((long) n / 100) / 10.0;
        boolean isRound = (d * 10) %10 == 0;//true if the decimal part is equal to 0 (then it's trimmed anyway)
        return (d < 1000? //this determines the class, i.e. 'k', 'm' etc
                ((d > 99.9 || isRound || (!isRound && d > 9.99)? //this decides whether to trim the decimals
                        (int) d * 10 / 10 : d + "" // (int) d * 10 / 10 drops the decimal
                ) + "" + c[iteration])
                : numberFormat(d, iteration+1));

    }

}
