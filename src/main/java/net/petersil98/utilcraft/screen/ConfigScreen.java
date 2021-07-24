package net.petersil98.utilcraft.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.config.Config;
import net.petersil98.utilcraft.screen.widget.ColorChooser;
import net.petersil98.utilcraft.utils.TextUtils;

import javax.annotation.Nonnull;

public class ConfigScreen extends Screen {

    private EditBox deaths;
    private ColorChooser colorChooser;
    private final Screen previousScreen;
    private final TranslatableComponent GUI_SECTION = new TranslatableComponent("config.section.gui");
    private final TranslatableComponent PLAYER_DEATHS_DESCRIPTION = new TranslatableComponent("config.number_player_deaths.description");
    private final TranslatableComponent PLAYER_DEATHS_TITLE = new TranslatableComponent("config.number_player_deaths.title");

    public ConfigScreen(Screen previousScreen) {
        super(new TranslatableComponent(String.format("screen.%s.config", Utilcraft.MOD_ID)));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        this.deaths = new EditBox(this.font, this.width / 2 - 100, 116, 200, 20, new TranslatableComponent("config.number_player_deaths"));
        this.deaths.setMaxLength(2);
        this.deaths.setFocus(true);
        this.deaths.setValue(String.valueOf(Config.DEATHS_OVERLAY_PLAYERS.get()));
        this.addWidget(this.deaths);
        this.setInitialFocus(this.deaths);

        this.colorChooser = new ColorChooser(this.font, this.width/2 - 50,200, 100, 20,new TextComponent("Death Point Ray Color"));
        this.addWidget(this.colorChooser);
    }

    @Override
    public void render(@Nonnull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        TextUtils.drawCenterText(matrixStack, this.font, this.title.getString(),  this.width, 2, 0xFFFFFF);
        drawString(matrixStack, this.font, this.GUI_SECTION, this.width / 2 - this.font.width(this.GUI_SECTION.getString())/2, 60, 0xCCF7B5);
        drawString(matrixStack, this.font, this.PLAYER_DEATHS_TITLE, this.width / 2 - this.font.width(this.PLAYER_DEATHS_TITLE.getString())/2, 90, 0xCCF7B5);
        drawString(matrixStack, this.font, this.PLAYER_DEATHS_DESCRIPTION, this.width / 2 - this.font.width(this.PLAYER_DEATHS_DESCRIPTION.getString())/2, 145, 0xCCF7B5);
        this.deaths.render(matrixStack, mouseX, mouseY, partialTicks);
        this.colorChooser.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void removed() {
        this.checkInput();
    }

    private void checkInput() {
        try {
            int config = Integer.parseInt(this.deaths.getValue());
            if (config >= 0 && config <= Config.DEATHS_OVERLAY_PLAYERS_MAX) {
                Config.DEATHS_OVERLAY_PLAYERS.set(config);
                Config.DEATHS_OVERLAY_PLAYERS.save();
            }
        } catch (NumberFormatException ignored) {}
        Config.DEATH_RAY_COLOR.set(this.colorChooser.getColor());
        Config.DEATH_RAY_COLOR.save();
    }

    @Override
    public void tick() {
        this.deaths.tick();
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.previousScreen);
    }
}
