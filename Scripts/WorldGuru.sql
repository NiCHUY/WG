CREATE TABLE [dbo].[User](
	[ID] [int] NOT NULL,
	[Nickname] [char](20) NOT NULL,
	[Password] [nchar](16) NOT NULL,
	[FlagPassed] [int] NOT NULL,
	[MapPassed] [int] NOT NULL,
	[FactQuizPercent] [int] NOT NULL,
	[MapPercent] [int] NOT NULL,
	[FlagFailed] [int] NOT NULL,
	[MapFailed] [int] NOT NULL,
	[FactQuizFailed] [int] NOT NULL,
	[CompareFactsFailed] [int] NOT NULL,
	[UserMark] [real] NOT NULL,
	[IsAdmin] [int] NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Country](
	[Country_ID] [int] NOT NULL,
	[Name] [char](50) NOT NULL,
	[Area] [char](50) NOT NULL,
	[Population] [int] NOT NULL,
	[Continent] [char](50) NOT NULL,
	[Fact] [char](50) NOT NULL,
	[Flag] [image] NOT NULL,
	[Territory] [image] NOT NULL,
 CONSTRAINT [PK_Country] PRIMARY KEY CLUSTERED 
(
	[Country_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


CREATE TABLE [dbo].[CompareFactQuiz](
	[CompareFactQuiz_ID] [int] NOT NULL,
	[Question1] [nchar](1000) NOT NULL,
	[Question2] [nchar](1000) NOT NULL,
	[Question3] [nchar](1000) NOT NULL,
	[Question4] [nchar](1000) NOT NULL,
	[Variant1] [int] NOT NULL,
	[Variant2] [int] NOT NULL,
	[Variant3] [int] NOT NULL,
	[Variant4] [int] NOT NULL,
	[Answer1] [int] NOT NULL,
	[Answer2] [int] NOT NULL,
	[Answer3] [int] NOT NULL,
	[Answer4] [int] NOT NULL,
 CONSTRAINT [PK_CompareFactQuiz] PRIMARY KEY CLUSTERED 
(
	[CompareFactQuiz_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[CompareFactQuiz]  WITH CHECK ADD  CONSTRAINT [FK_CompareFactQuiz_Country] FOREIGN KEY([Variant1])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[CompareFactQuiz] CHECK CONSTRAINT [FK_CompareFactQuiz_Country]
GO

ALTER TABLE [dbo].[CompareFactQuiz]  WITH CHECK ADD  CONSTRAINT [FK_CompareFactQuiz_Country1] FOREIGN KEY([Variant2])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[CompareFactQuiz] CHECK CONSTRAINT [FK_CompareFactQuiz_Country1]
GO

ALTER TABLE [dbo].[CompareFactQuiz]  WITH CHECK ADD  CONSTRAINT [FK_CompareFactQuiz_Country2] FOREIGN KEY([Variant3])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[CompareFactQuiz] CHECK CONSTRAINT [FK_CompareFactQuiz_Country2]
GO

ALTER TABLE [dbo].[CompareFactQuiz]  WITH CHECK ADD  CONSTRAINT [FK_CompareFactQuiz_Country3] FOREIGN KEY([Variant4])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[CompareFactQuiz] CHECK CONSTRAINT [FK_CompareFactQuiz_Country3]
GO

CREATE TABLE [dbo].[FactQuiz](
	[FactQuiz_ID] [int] NOT NULL,
	[FactText] [nchar](1000) NOT NULL,
	[Variant1] [int] NOT NULL,
	[Variant2] [int] NOT NULL,
	[Variant3] [int] NOT NULL,
	[Variant4] [int] NOT NULL,
	[Answer] [int] NOT NULL,
 CONSTRAINT [PK_FactQuiz] PRIMARY KEY CLUSTERED 
(
	[FactQuiz_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[FactQuiz]  WITH CHECK ADD  CONSTRAINT [FK_FactQuiz_Country] FOREIGN KEY([Variant1])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[FactQuiz] CHECK CONSTRAINT [FK_FactQuiz_Country]
GO

ALTER TABLE [dbo].[FactQuiz]  WITH CHECK ADD  CONSTRAINT [FK_FactQuiz_Country1] FOREIGN KEY([Variant2])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[FactQuiz] CHECK CONSTRAINT [FK_FactQuiz_Country1]
GO

ALTER TABLE [dbo].[FactQuiz]  WITH CHECK ADD  CONSTRAINT [FK_FactQuiz_Country2] FOREIGN KEY([Variant3])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[FactQuiz] CHECK CONSTRAINT [FK_FactQuiz_Country2]
GO

ALTER TABLE [dbo].[FactQuiz]  WITH CHECK ADD  CONSTRAINT [FK_FactQuiz_Country3] FOREIGN KEY([Variant4])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[FactQuiz] CHECK CONSTRAINT [FK_FactQuiz_Country3]
GO

CREATE TABLE [dbo].[FlagQuiz](
	[FlagQuiz_ID] [int] NOT NULL,
	[Country_ID] [int] NOT NULL,
	[Variant1] [int] NOT NULL,
	[Variant2] [int] NOT NULL,
	[Variant3] [int] NOT NULL,
	[Variant4] [int] NOT NULL,
 CONSTRAINT [PK_FlagQuiz] PRIMARY KEY CLUSTERED 
(
	[FlagQuiz_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[FlagQuiz]  WITH CHECK ADD  CONSTRAINT [FK_FlagQuiz_Country] FOREIGN KEY([Country_ID])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[FlagQuiz] CHECK CONSTRAINT [FK_FlagQuiz_Country]
GO

ALTER TABLE [dbo].[FlagQuiz]  WITH CHECK ADD  CONSTRAINT [FK_FlagQuiz_Country1] FOREIGN KEY([Variant1])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[FlagQuiz] CHECK CONSTRAINT [FK_FlagQuiz_Country1]
GO

ALTER TABLE [dbo].[FlagQuiz]  WITH CHECK ADD  CONSTRAINT [FK_FlagQuiz_Country2] FOREIGN KEY([Variant2])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[FlagQuiz] CHECK CONSTRAINT [FK_FlagQuiz_Country2]
GO

ALTER TABLE [dbo].[FlagQuiz]  WITH CHECK ADD  CONSTRAINT [FK_FlagQuiz_Country3] FOREIGN KEY([Variant3])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[FlagQuiz] CHECK CONSTRAINT [FK_FlagQuiz_Country3]
GO

ALTER TABLE [dbo].[FlagQuiz]  WITH CHECK ADD  CONSTRAINT [FK_FlagQuiz_Country4] FOREIGN KEY([Variant4])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[FlagQuiz] CHECK CONSTRAINT [FK_FlagQuiz_Country4]
GO

CREATE TABLE [dbo].[MapQuiz](
	[MapQuiz_ID] [int] NOT NULL,
	[Country_ID] [int] NOT NULL,
	[ClueFact] [nchar](1000) NOT NULL,
	[ClueArea] [int] NOT NULL,
	[CluePopulation] [int] NOT NULL,
	[ClueContinent] [char](50) NOT NULL,
 CONSTRAINT [PK_MapQuiz] PRIMARY KEY CLUSTERED 
(
	[MapQuiz_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MapQuiz]  WITH CHECK ADD  CONSTRAINT [FK_MapQuiz_Country] FOREIGN KEY([Country_ID])
REFERENCES [dbo].[Country] ([Country_ID])
GO

ALTER TABLE [dbo].[MapQuiz] CHECK CONSTRAINT [FK_MapQuiz_Country]
GO